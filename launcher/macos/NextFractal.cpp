#include <CoreFoundation/CoreFoundation.h>
#include <mach-o/dyld.h>
#include <jni.h>
#include <dlfcn.h>
#include <dirent.h>
#include <stdexcept>
#include <iostream>

struct start_args {
    JavaVMInitArgs vm_args;
    char *launch_class;

    start_args(const char **args, const char *classname)
    {
        vm_args.options = 0;
        vm_args.nOptions = 0;
        launch_class = 0;

        int arg_count = 0;
        const char **atarg = args;
        while (*atarg++) arg_count++;

        JavaVMOption *options = new JavaVMOption[arg_count];
        vm_args.nOptions = arg_count;
        for (int i = 0; i < vm_args.nOptions; i++)
            options[i].optionString = 0;
        vm_args.options = options;

        while (*args) {
            options->optionString = strdup(*args);
            printf("%s\n", options->optionString);
            options++;
            args++;
        }
        vm_args.version = JNI_VERSION_1_6;
        vm_args.ignoreUnrecognized = JNI_TRUE;
        launch_class = strdup(classname);
    }

    ~start_args() {
        if (launch_class)
            free(launch_class);
        for (int i = 0; i < vm_args.nOptions; i++)
            if (vm_args.options[i].optionString)
                free(vm_args.options[i].optionString);
        if (vm_args.options)
            delete[] vm_args.options;
    }

    start_args(const start_args &rhs) {
        vm_args.options = 0;
        launch_class = 0;

        vm_args.options = new JavaVMOption[rhs.vm_args.nOptions];
        vm_args.nOptions = rhs.vm_args.nOptions;
        for (int i = 0; i < vm_args.nOptions; i++) {
            vm_args.options[i].optionString = strdup(rhs.vm_args.options[i].optionString);
        }
        vm_args.version = rhs.vm_args.version;
        vm_args.ignoreUnrecognized = rhs.vm_args.ignoreUnrecognized;
        launch_class = strdup(rhs.launch_class);
    }

    start_args &operator=(const start_args &rhs) {
        for (int i = 0; i < vm_args.nOptions; i++) {
            if (vm_args.options[i].optionString) free(vm_args.options[i].optionString);
        }
        delete[] vm_args.options;

        vm_args.options = new JavaVMOption[rhs.vm_args.nOptions];
        vm_args.nOptions = rhs.vm_args.nOptions;
        for (int i = 0; i < vm_args.nOptions; i++)
            vm_args.options[i].optionString = 0;
        for (int i = 0; i < vm_args.nOptions; i++)
            vm_args.options[i].optionString = strdup(rhs.vm_args.options[i].optionString);
        vm_args.version = rhs.vm_args.version;
        vm_args.ignoreUnrecognized = rhs.vm_args.ignoreUnrecognized;
        if (launch_class) free(launch_class);
        launch_class = strdup(rhs.launch_class);
        return *this;
    }
};

void ShowAlert(const std::string message, const std::runtime_error& error) {
    std::string alertMessage = std::string(message).append("\n\nCause: ").append(error.what());
    CFStringRef cfTitle = CFStringCreateWithCString(NULL, "Oops something is wrong...", kCFStringEncodingUTF8);
    CFStringRef cfMessage = CFStringCreateWithCString(NULL, alertMessage.c_str(), kCFStringEncodingUTF8);
    CFUserNotificationDisplayNotice(0, kCFUserNotificationStopAlertLevel, NULL, NULL, NULL, cfTitle, cfMessage, NULL);
    CFRelease(cfTitle);
    CFRelease(cfMessage);
}

std::string ExecuteCommand(const char* cmd) {
    char buffer[128];
    std::string result = "";
    std::shared_ptr<FILE> pipe(popen(cmd, "r"), pclose);
    if (!pipe) throw std::runtime_error("Failed to open pipe");
    while (!feof(pipe.get())) {
        if (fgets(buffer, 128, pipe.get()) != NULL)
            result += buffer;
    }
    return result;
}

typedef int (JNICALL * JNICreateJavaVM)(JavaVM** jvm, JNIEnv** env, JavaVMInitArgs* initargs);

void * start_java(void *start_args) {
    try {
        struct start_args *args = (struct start_args *)start_args;

        std::string path = ExecuteCommand("/usr/libexec/java_home -v 1.8");
        path.erase(std::remove(path.begin(), path.end(), '\n'), path.end());
        std::cout << "Found java \"" << path << "\"" << std::endl;

        std::string libPath = path + "/jre/lib/server/libjvm.dylib";
        std::cout << "Use library \"" << libPath << "\"" << std::endl;

        void* lib_handle = dlopen(libPath.c_str(), RTLD_LOCAL | RTLD_LAZY);
        if (!lib_handle) {
            throw std::runtime_error("Failed to open library");
        }

        JNICreateJavaVM createJavaJVM = (JNICreateJavaVM)dlsym(lib_handle, "JNI_CreateJavaVM");
        if (!createJavaJVM) {
            dlclose(lib_handle);
            throw std::runtime_error("Function JNI_CreateJavaVM not found");
        }

        int res;
        JavaVM *jvm;
        JNIEnv *env;

        res = createJavaJVM(&jvm, &env, &args->vm_args);
        if (res < 0) {
            dlclose(lib_handle);
            throw std::runtime_error("Failed to create jvm");
        }
        /* load the launch class */
        jclass main_class;
        jmethodID main_method_id;
        main_class = env->FindClass(args->launch_class);
        if (main_class == NULL) {
            jvm->DestroyJavaVM();
            dlclose(lib_handle);
            throw std::runtime_error("Main class not found");
        }
        /* get main method */
        main_method_id = env->GetStaticMethodID(main_class, "main", "([Ljava/lang/String;)V");
        if (main_method_id == NULL) {
            jvm->DestroyJavaVM();
            dlclose(lib_handle);
            throw std::runtime_error("Method main not found");
        }
        /* make the initial argument */
        jobject empty_args = env->NewObjectArray(0, env->FindClass("java/lang/String"), NULL);
        /* call the method */
        env->CallStaticVoidMethod(main_class, main_method_id, empty_args);
        /* Don't forget to destroy the JVM at the end */
        jvm->DestroyJavaVM();

        dlclose(lib_handle);

        return (0);
    } catch (const std::runtime_error& e) {
        ShowAlert("Did you install Java JDK 8 or later?", e);
        exit(-1);
    }
}

std::string GetClasspath(std::string path) {
   std::string s = std::string();
   std::cout << "Creating classpath..." << std::endl;
   DIR* dirFile = opendir(path.c_str());
   if (dirFile) {
      struct dirent* hFile;
      std::cout << "Scanning folder " << path << "..." << std::endl;
      while ((hFile = readdir(dirFile)) != NULL) {
         if (!strcmp(hFile->d_name, "." )) continue;
         if (!strcmp(hFile->d_name, "..")) continue;
         if (hFile->d_name[0] == '.') continue;
         if (strstr(hFile->d_name, ".jar")) {
            std::cout << "Found jar " << hFile->d_name << std::endl;
            s.append(path);
            s.append("/");
            s.append(hFile->d_name);
            s.append(":");
         }
      }
      s.append(".");
      closedir(dirFile);
   }
   return s;
}

std::string GetExePath() {
    char* result = (char *)malloc(PATH_MAX + 1);
    uint32_t size = PATH_MAX;
    if (_NSGetExecutablePath(result, &size) < 0) {
        throw std::runtime_error("Unable to get executable path");
    }
    return std::string(result, (size > 0) ? size : 0);
}

std::string GetBasePath(std::string exePath) {
    return exePath.substr(0, exePath.find_last_of("/"));
}

int main(int argc, char **argv) {
    try {
        std::string memMaxArg = std::string();
        char * varMemMax = getenv("NEXTFRACTAL_MAX_MEMORY");
        int varMemMaxLen = varMemMax != NULL ? strlen(varMemMax) : 0;
        if (varMemMaxLen > 0) {
            memMaxArg.append("-Xmx");
            memMaxArg.append(std::to_string(std::stoi(varMemMax)));
            memMaxArg.append("m");
        } else {
            memMaxArg.append("-Xmx3g");
        }
        std::string basePath = GetBasePath(GetExePath());
        std::cout << "Base path " << basePath << std::endl;
        std::string jarsPath = basePath + "/../Resources";
        std::string classpathArg = "-Djava.class.path=" + GetClasspath(jarsPath);
        std::string libPathArg = "-Djava.library.path=" + basePath + "/../Resources";
        std::string locPathArg = "-Dbrowser.location=" + basePath + "/../../../examples";
        const char *vm_arglist[] = {
            "-Djava.util.logging.config.class=com.nextbreakpoint.nextfractal.runtime.LogConfig",
            classpathArg.c_str(),
            libPathArg.c_str(),
            locPathArg.c_str(),
            memMaxArg.c_str(),
            0
        };
        struct start_args args(vm_arglist, "com/nextbreakpoint/nextfractal/runtime/javafx/NextFractalApp");
        pthread_t thr;
        pthread_create(&thr, NULL, start_java, &args);
        CFRunLoopRun();
    } catch (const std::runtime_error& e) {
        ShowAlert("Did you install Java JDK 8 or later?", e);
    }
}

#/bin/sh
export BUILD_DIR=`pwd`/build

mkdir -p $BUILD_DIR/macos

cp macos/* $BUILD_DIR/macos

cd $BUILD_DIR/macos

make


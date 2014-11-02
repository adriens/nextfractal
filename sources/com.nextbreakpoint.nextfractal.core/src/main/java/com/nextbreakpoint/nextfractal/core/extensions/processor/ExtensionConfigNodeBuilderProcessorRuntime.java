/*
 * NextFractal 7.0 
 * http://www.nextbreakpoint.com
 *
 * Copyright 2001, 2015 Andrea Medeghini
 * andrea@nextbreakpoint.com
 *
 * This file is part of NextFractal.
 *
 * NextFractal is an application for creating fractals and other graphics artifacts.
 *
 * NextFractal is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * NextFractal is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with NextFractal.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.nextbreakpoint.nextfractal.core.extensions.processor;

import java.io.File;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.nextbreakpoint.nextfractal.core.devtools.DevToolsException;
import com.nextbreakpoint.nextfractal.core.devtools.ProcessorCardinality;
import com.nextbreakpoint.nextfractal.core.devtools.ProcessorDescriptor;
import com.nextbreakpoint.nextfractal.core.devtools.ProcessorParameters;
import com.nextbreakpoint.nextfractal.core.devtools.ProcessorTemplateLoader;
import com.nextbreakpoint.nextfractal.core.extensionPoints.processor.ProcessorExtensionRuntime;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class ExtensionConfigNodeBuilderProcessorRuntime extends ProcessorExtensionRuntime {
	/**
	 * @see com.nextbreakpoint.nextfractal.core.extensionPoints.processor.ProcessorExtensionRuntime#process(java.io.File, com.nextbreakpoint.nextfractal.core.devtools.ProcessorDescriptor, java.util.List, java.util.Map)
	 */
	@Override
	public void process(File path, ProcessorParameters parameters, Map<String, String> variables) throws DevToolsException {
		try {
			ProcessorDescriptor element = parameters.getElement();
			List<ProcessorDescriptor> elements = parameters.getElements();
			HashMap<String, Object> map = new HashMap<String, Object>();
			Set<String> imports = new HashSet<String>();
			prepare(imports, element);
			prepare(imports, elements);
			List<String> sortedImports = new LinkedList<String>(imports);
			Collections.sort(sortedImports);
			map.putAll(variables);
			map.put("imports", sortedImports);
			map.put("extension", element);
			map.put("subelements", elements);
			if (!variables.containsKey("generateAbstractClass") && element.isConfigurableExtension()) {
				File packagePath = new File(path, element.getExtensionConfigPackageName().replace('.', '/'));
				packagePath.mkdirs();
				Configuration config = new Configuration();
				config.setTemplateLoader(new ProcessorTemplateLoader());
				Template template = config.getTemplate("templates/ExtensionConfigNodeBuilder.ftl");
				template.process(map, new PrintWriter(new File(packagePath, element.getExtensionConfigClassName() + "NodeBuilderRuntime.java")));
			}
		}
		catch (Exception e) {
			throw new DevToolsException(e);
		}
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.extensionPoints.processor.ProcessorExtensionRuntime#getName()
	 */
	@Override
	public String getName() {
		return "ExtensionConfigNodeBuilder";
	}

	private void prepare(Set<String> imports, ProcessorDescriptor descriptor) {
		if (descriptor.isConfigurableExtension()) {
			imports.add("com.nextbreakpoint.nextfractal.core.tree.Node");
			imports.add("com.nextbreakpoint.nextfractal.core.tree.NodeBuilder");
			imports.add("com.nextbreakpoint.nextfractal.core.extension.ExtensionConfig");
			imports.add("com.nextbreakpoint.nextfractal.core.tree.extension.NodeBuilderExtensionRuntime");
			imports.add("com.nextbreakpoint.nextfractal.core.util.AbstractExtensionConfigNodeBuilder");
			imports.add(descriptor.getResourcesPackageName() + "." + descriptor.getResourcesClassName());
		}
	}

	private void prepare(Set<String> imports, List<ProcessorDescriptor> descriptors) {
		for (ProcessorDescriptor descriptor : descriptors) {
			if (descriptor.isExtensionElement()) {
				imports.add(descriptor.getConfigElementPackageName() + "." + descriptor.getConfigElementClassName() + "Node");
				imports.add("com.nextbreakpoint.nextfractal.core.tree.NodeValue");
				imports.add("com.nextbreakpoint.nextfractal.core.extension.ExtensionReference");
				imports.add("com.nextbreakpoint.nextfractal.core.common.ExtensionReferenceElementNodeValue");
			}
			else if (descriptor.isConfigurableExtensionElement()) {
				imports.add(descriptor.getConfigElementPackageName() + "." + descriptor.getConfigElementClassName() + "Node");
				imports.add("com.nextbreakpoint.nextfractal.core.tree.NodeValue");
				imports.add("com.nextbreakpoint.nextfractal.core.extension.ConfigurableExtensionReference");
				imports.add("com.nextbreakpoint.nextfractal.core.common.ExtensionReferenceElementNodeValue");
			}
			else if (descriptor.isComplexElement()) {
				imports.add(descriptor.getConfigElementPackageName() + "." + descriptor.getConfigElementClassName());
				imports.add(descriptor.getConfigElementPackageName() + "." + descriptor.getConfigElementClassName() + "Node");
				imports.add("com.nextbreakpoint.nextfractal.core.tree.NodeValue");
				imports.add("com.nextbreakpoint.nextfractal.core.util.AbstractConfigElementNode");
				if (descriptor.getCardinality() == ProcessorCardinality.ONE) {
					imports.add("com.nextbreakpoint.nextfractal.core.util.AbstractConfigElementSingleNode");
					imports.add("com.nextbreakpoint.nextfractal.core.util.ConfigElementSingleNodeValue");
				} else if (descriptor.getCardinality() == ProcessorCardinality.MANY) {
					imports.add("com.nextbreakpoint.nextfractal.core.util.AbstractConfigElementListNode");
					imports.add("com.nextbreakpoint.nextfractal.core.util.ConfigElementListNodeValue");
				}
			}
			else if (descriptor.isSimpleElement()) {
				imports.add(descriptor.getConfigElementPackageName() + "." + descriptor.getConfigElementClassName() + "Node");
				//imports.add("com.nextbreakpoint.nextfractal.core.util.AbstractConfigElementNode");
			}
		}
	}
}
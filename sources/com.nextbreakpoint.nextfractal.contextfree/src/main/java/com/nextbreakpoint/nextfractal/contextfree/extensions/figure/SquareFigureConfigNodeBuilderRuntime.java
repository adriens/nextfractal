/*
 * $Id:$
 *
 */
package com.nextbreakpoint.nextfractal.contextfree.extensions.figure;

import com.nextbreakpoint.nextfractal.contextfree.extensions.ContextFreeExtensionResources;
import com.nextbreakpoint.nextfractal.core.extensionPoints.nodeBuilder.NodeBuilderExtensionRuntime;
import com.nextbreakpoint.nextfractal.core.runtime.extension.ExtensionConfig;
import com.nextbreakpoint.nextfractal.core.runtime.model.AttributeNode;
import com.nextbreakpoint.nextfractal.core.runtime.model.NodeBuilder;
import com.nextbreakpoint.nextfractal.core.runtime.model.NodeEditor;
import com.nextbreakpoint.nextfractal.core.runtime.model.NodeObject;
import com.nextbreakpoint.nextfractal.core.runtime.util.AbstractExtensionConfigNodeBuilder;

/**
 * @author Andrea Medeghini
 */
public class SquareFigureConfigNodeBuilderRuntime extends NodeBuilderExtensionRuntime {
	/**
	 * @see com.nextbreakpoint.nextfractal.core.extensionPoints.nodeBuilder.NodeBuilderExtensionRuntime#createNodeBuilder(com.nextbreakpoint.nextfractal.core.runtime.extension.ExtensionConfig)
	 */
	@Override
	public NodeBuilder createNodeBuilder(final ExtensionConfig config) {
		return new ConfigNodeBuilder((SquareFigureConfig) config);
	}

	private class ConfigNodeBuilder extends AbstractExtensionConfigNodeBuilder<SquareFigureConfig> {
		/**
		 * @param config
		 */
		public ConfigNodeBuilder(final SquareFigureConfig config) {
			super(config);
		}


		/**
		 * @see com.nextbreakpoint.nextfractal.core.runtime.util.AbstractExtensionConfigNodeBuilder#createNodes(com.nextbreakpoint.nextfractal.core.runtime.model.NodeObject)
		 */
		@Override
		public void createNodes(final NodeObject parentNode) {
			parentNode.appendChildNode(new NameElementNode(getConfig()));
		}

		private class NameElementNode extends AttributeNode {
			/**
			 * @param config
			 */
			public NameElementNode(final SquareFigureConfig config) {
				super(config.getExtensionId() + ".name");
				setNodeLabel(ContextFreeExtensionResources.getInstance().getString("node.label.NameElement"));
			}

			@Override
			protected NodeEditor createNodeEditor() {
				return null;
			}

			@Override
			public String getValueAsString() {
				return "SQUARE";
			}
		}
	}
}

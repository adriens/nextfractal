/*
 * $Id:$
 *
 */
package com.nextbreakpoint.nextfractal.contextfree.extensions.pathAdjustment;

import com.nextbreakpoint.nextfractal.contextfree.extensions.ContextFreeExtensionResources;
import com.nextbreakpoint.nextfractal.core.elements.FloatElementNode;
import com.nextbreakpoint.nextfractal.core.extensionPoints.nodeBuilder.NodeBuilderExtensionRuntime;
import com.nextbreakpoint.nextfractal.core.runtime.extension.ExtensionConfig;
import com.nextbreakpoint.nextfractal.core.runtime.model.NodeBuilder;
import com.nextbreakpoint.nextfractal.core.runtime.model.NodeObject;
import com.nextbreakpoint.nextfractal.core.runtime.util.AbstractExtensionConfigNodeBuilder;

/**
 * @author Andrea Medeghini
 */
public class XPathAdjustmentConfigNodeBuilderRuntime extends NodeBuilderExtensionRuntime {
	/**
	 * @see com.nextbreakpoint.nextfractal.core.extensionPoints.nodeBuilder.NodeBuilderExtensionRuntime#createNodeBuilder(com.nextbreakpoint.nextfractal.core.runtime.extension.ExtensionConfig)
	 */
	@Override
	public NodeBuilder createNodeBuilder(final ExtensionConfig config) {
		return new ConfigNodeBuilder((XPathAdjustmentConfig) config);
	}

	private class ConfigNodeBuilder extends AbstractExtensionConfigNodeBuilder<XPathAdjustmentConfig> {
		/**
		 * @param config
		 */
		public ConfigNodeBuilder(final XPathAdjustmentConfig config) {
			super(config);
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.runtime.util.AbstractExtensionConfigNodeBuilder#createNodes(com.nextbreakpoint.nextfractal.core.runtime.model.NodeObject)
		 */
		@Override
		public void createNodes(final NodeObject parentNode) {
			parentNode.appendChildNode(new ValueElementNode(getConfig()));
		}

		private class ValueElementNode extends FloatElementNode {
			/**
			 * @param config
			 */
			public ValueElementNode(final XPathAdjustmentConfig config) {
				super(config.getExtensionId() + ".value", config.getValueElement());
				setNodeLabel(ContextFreeExtensionResources.getInstance().getString("node.label.ValueElement"));
			}
		}
	}
}

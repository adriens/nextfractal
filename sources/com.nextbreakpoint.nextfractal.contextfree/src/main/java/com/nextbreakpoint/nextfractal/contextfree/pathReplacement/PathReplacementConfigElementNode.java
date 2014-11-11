/*
 * $Id:$
 *
 */
package com.nextbreakpoint.nextfractal.contextfree.pathReplacement;

import com.nextbreakpoint.nextfractal.contextfree.ContextFreeResources;
import com.nextbreakpoint.nextfractal.contextfree.extensionPoints.pathReplacement.PathReplacementExtensionConfig;
import com.nextbreakpoint.nextfractal.core.elements.ConfigurableExtensionReferenceElementNode;
import com.nextbreakpoint.nextfractal.core.runtime.extension.ConfigurableExtensionReference;
import com.nextbreakpoint.nextfractal.core.runtime.model.NodeEditor;
import com.nextbreakpoint.nextfractal.core.runtime.model.NodeObject;
import com.nextbreakpoint.nextfractal.core.runtime.model.NodeValue;
import com.nextbreakpoint.nextfractal.core.runtime.util.AbstractConfigElementNode;

/**
 * @author Andrea Medeghini
 */
public class PathReplacementConfigElementNode extends AbstractConfigElementNode<PathReplacementConfigElement> {
	private static final String NODE_ID = PathReplacementConfigElement.CLASS_ID;
	private static final String NODE_CLASS = "node.class.PathReplacementElement";
	private static final String NODE_LABEL = ContextFreeResources.getInstance().getString("node.label.PathReplacementElement");
	private final PathReplacementConfigElement pathReplacement;

	/**
	 * Constructs a new effect node.
	 * 
	 * @param pathReplacement the pathReplacement element.
	 */
	public PathReplacementConfigElementNode(final PathReplacementConfigElement pathReplacement) {
		super(PathReplacementConfigElementNode.NODE_ID);
		if (pathReplacement == null) {
			throw new IllegalArgumentException("pathReplacement is null");
		}
		this.pathReplacement = pathReplacement;
		setNodeLabel(PathReplacementConfigElementNode.NODE_LABEL);
		setNodeClass(PathReplacementConfigElementNode.NODE_CLASS);
		setNodeValue(new PathReplacementConfigElementNodeValue(pathReplacement));
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object o) {
		if (o instanceof PathReplacementConfigElementNode) {
			return (pathReplacement == ((PathReplacementConfigElementNode) o).pathReplacement);
		}
		return false;
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.runtime.util.AbstractConfigElementNode#getConfigElement()
	 */
	@Override
	public PathReplacementConfigElement getConfigElement() {
		return pathReplacement;
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.runtime.model.NodeObject#addDescription(java.lang.StringBuilder)
	 */
	@Override
	protected void addDescription(final StringBuilder builder) {
		if (getChildNodeCount() > 0) {
			builder.append(getChildNode(0).getLabel());
		}
		else {
			super.addDescription(builder);
		}
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.runtime.model.NodeObject#updateNode()
	 */
	@Override
	protected void updateChildNodes() {
		createChildNodes((PathReplacementConfigElementNodeValue) getNodeValue());
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.runtime.model.DefaultNode#createNodeEditor()
	 */
	@Override
	protected NodeEditor createNodeEditor() {
		return new PathReplacementNodeEditor(this);
	}

	/**
	 * @param value
	 */
	protected void createChildNodes(final PathReplacementConfigElementNodeValue value) {
		removeAllChildNodes();
		appendChildNode(new ExtensionElementNode(PathReplacementConfigElementNode.NODE_ID + ".extension", value.getValue()));
	}

	private static class PathReplacementNodeEditor extends NodeEditor {
		/**
		 * @param node
		 */
		public PathReplacementNodeEditor(final NodeObject node) {
			super(node);
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.runtime.model.NodeEditor#createChildNode(com.nextbreakpoint.nextfractal.core.runtime.model.NodeValue)
		 */
		@Override
		protected NodeObject createChildNode(final NodeValue<?> value) {
			return null;
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.runtime.model.NodeEditor#createNodeValue(Object)
		 */
		@Override
		public NodeValue<?> createNodeValue(final Object value) {
			return new PathReplacementConfigElementNodeValue((PathReplacementConfigElement) value);
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.runtime.model.NodeEditor#getNodeValueType()
		 */
		@Override
		public Class<?> getNodeValueType() {
			return PathReplacementConfigElementNodeValue.class;
		}
	}

	private static class ExtensionElementNode extends ConfigurableExtensionReferenceElementNode<PathReplacementExtensionConfig> {
		public static final String NODE_CLASS = "node.class.PathReplacementReference";

		/**
		 * @param nodeId
		 * @param pathReplacement
		 */
		public ExtensionElementNode(final String nodeId, final PathReplacementConfigElement pathReplacement) {
			super(nodeId, pathReplacement.getExtensionElement());
			setNodeClass(ExtensionElementNode.NODE_CLASS);
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.elements.ConfigurableExtensionReferenceElementNode#createNodeValue(com.nextbreakpoint.nextfractal.core.runtime.extension.ConfigurableExtensionReference)
		 */
		@Override
		protected NodeValue<?> createNodeValue(final ConfigurableExtensionReference<PathReplacementExtensionConfig> value) {
			return new PathReplacementExtensionReferenceNodeValue(value);
		}
	}
}

/*
 * $Id:$
 *
 */
package com.nextbreakpoint.nextfractal.contextfree.elements;

import com.nextbreakpoint.nextfractal.contextfree.ContextFreeResources;
import com.nextbreakpoint.nextfractal.core.runtime.ElementChangeEvent;
import com.nextbreakpoint.nextfractal.core.runtime.ElementChangeListener;
import com.nextbreakpoint.nextfractal.core.runtime.ValueConfigElement;
import com.nextbreakpoint.nextfractal.core.runtime.model.AttributeNode;
import com.nextbreakpoint.nextfractal.core.runtime.model.NodeAction;
import com.nextbreakpoint.nextfractal.core.runtime.model.NodeEditor;
import com.nextbreakpoint.nextfractal.core.runtime.model.NodeObject;
import com.nextbreakpoint.nextfractal.core.runtime.model.NodeSession;
import com.nextbreakpoint.nextfractal.core.runtime.model.NodeValue;

/**
 * @author Andrea Medeghini
 */
public abstract class StrokeCapElementNode extends AttributeNode {
	public static final String NODE_CLASS = "node.class.StrokeCapElement";
	private final ConfigElementListener listener;
	private final ValueConfigElement<String> configElement;

	/**
	 * @param nodeId
	 * @param configElement
	 */
	public StrokeCapElementNode(final String nodeId, final ValueConfigElement<String> configElement) {
		super(nodeId);
		setNodeClass(StrokeCapElementNode.NODE_CLASS);
		this.configElement = configElement;
		listener = new ConfigElementListener();
		setNodeValue(new StrokeCapElementNodeValue(configElement.getValue()));
		setNodeLabel(ContextFreeResources.getInstance().getString("node.label.StrokeCapElement"));
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.runtime.model.NodeObject#dispose()
	 */
	@Override
	public void dispose() {
		if (configElement != null) {
			configElement.removeChangeListener(listener);
		}
		super.dispose();
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.runtime.model.NodeObject#setSession(com.nextbreakpoint.nextfractal.core.runtime.model.NodeSession)
	 */
	@Override
	public void setSession(final NodeSession session) {
		if (session != null) {
			configElement.addChangeListener(listener);
		}
		else {
			configElement.removeChangeListener(listener);
		}
		super.setSession(session);
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.runtime.model.NodeObject#nodeAdded()
	 */
	@Override
	protected void nodeAdded() {
		setNodeValue(new StrokeCapElementNodeValue(configElement.getValue()));
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.runtime.model.NodeObject#nodeRemoved()
	 */
	@Override
	protected void nodeRemoved() {
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.runtime.model.NodeObject#isEditable()
	 */
	@Override
	public boolean isEditable() {
		return true;
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.runtime.model.DefaultNode#getValueAsString()
	 */
	@Override
	public String getValueAsString() {
		if (getNodeValue() != null) {
			return String.valueOf(getNodeValue().getValue());
		}
		else {
			return "";
		}
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.runtime.model.DefaultNode#createNodeEditor()
	 */
	@Override
	protected NodeEditor createNodeEditor() {
		return new StrokeCapNodeEditor(this);
	}

	protected class StrokeCapNodeEditor extends NodeEditor {
		/**
		 * @param node
		 */
		public StrokeCapNodeEditor(final AttributeNode node) {
			super(node);
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.runtime.model.NodeEditor#doSetValue(java.lang.NodeValue)
		 */
		@Override
		protected void doSetValue(final NodeValue<?> value) {
			configElement.removeChangeListener(listener);
			configElement.setValue(((StrokeCapElementNodeValue) value).getValue());
			configElement.addChangeListener(listener);
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.runtime.model.NodeEditor#createChildNode(com.nextbreakpoint.nextfractal.core.runtime.model.NodeValue)
		 */
		@Override
		protected NodeObject createChildNode(final NodeValue<?> value) {
			return null;
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.runtime.model.NodeEditor#getNodeValueType()
		 */
		@Override
		public Class<?> getNodeValueType() {
			return StrokeCapElementNodeValue.class;
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.runtime.model.NodeEditor#createNodeValue(Object)
		 */
		@Override
		public NodeValue<?> createNodeValue(final Object value) {
			return new StrokeCapElementNodeValue((String) value);
		}
	}

	protected class ConfigElementListener implements ElementChangeListener {
		@Override
		public void valueChanged(final ElementChangeEvent e) {
			cancel();
			switch (e.getEventType()) {
				case ValueConfigElement.VALUE_CHANGED: {
					setNodeValue(new StrokeCapElementNodeValue((String) e.getParams()[0]));
					getSession().appendAction(new NodeAction(getNodeClass(), NodeAction.ACTION_SET_VALUE, e.getTimestamp(), getNodePath(), e.getParams()[0], e.getParams()[1]));
					break;
				}
				default: {
					break;
				}
			}
		}
	}
}
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
package com.nextbreakpoint.nextfractal.core.elements;

import java.awt.Font;

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
public abstract class FontElementNode extends AttributeNode {
	public static final String NODE_CLASS = "node.class.FontElement";
	private final ConfigElementListener listener;
	private final ValueConfigElement<Font> configElement;

	/**
	 * @param nodeId
	 * @param configElement
	 */
	public FontElementNode(final String nodeId, final ValueConfigElement<Font> configElement) {
		super(nodeId);
		setNodeClass(FontElementNode.NODE_CLASS);
		this.configElement = configElement;
		listener = new ConfigElementListener();
		setNodeValue(new FontElementNodeValue(configElement.getValue()));
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
		setNodeValue(new FontElementNodeValue(configElement.getValue()));
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
			return ((Font) getNodeValue().getValue()).getFontName();
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
		return new FontNodeEditor(this);
	}

	protected class FontNodeEditor extends NodeEditor {
		/**
		 * @param node
		 */
		public FontNodeEditor(final AttributeNode node) {
			super(node);
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.runtime.model.NodeEditor#doSetValue(java.lang.NodeValue)
		 */
		@Override
		protected void doSetValue(final NodeValue<?> value) {
			configElement.removeChangeListener(listener);
			configElement.setValue(((FontElementNodeValue) value).getValue());
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
			return FontElementNodeValue.class;
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.runtime.model.NodeEditor#createNodeValue(Object)
		 */
		@Override
		public NodeValue<?> createNodeValue(final Object value) {
			return new FontElementNodeValue((Font) value);
		}
	}

	protected class ConfigElementListener implements ElementChangeListener {
		@Override
		public void valueChanged(final ElementChangeEvent e) {
			cancel();
			switch (e.getEventType()) {
				case ValueConfigElement.VALUE_CHANGED: {
					setNodeValue(new FontElementNodeValue((Font) e.getParams()[0]));
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
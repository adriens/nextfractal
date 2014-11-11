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
package com.nextbreakpoint.nextfractal.core.ui.javafx.extensions.editor;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

import com.nextbreakpoint.nextfractal.core.runtime.model.NodeEditor;
import com.nextbreakpoint.nextfractal.core.ui.javafx.NodeEditorComponent;
import com.nextbreakpoint.nextfractal.core.ui.javafx.extensionPoints.editor.EditorExtensionRuntime;

/**
 * @author Andrea Medeghini
 */
public class FontElementEditorRuntime extends EditorExtensionRuntime {
	/**
	 * @see com.nextbreakpoint.nextfractal.core.ui.javafx.extensionPoints.editor.EditorExtensionRuntime#createEditor(com.nextbreakpoint.nextfractal.core.runtime.model.NodeEditor)
	 */
	@Override
	public NodeEditorComponent createEditor(final NodeEditor nodeEditor) {
		return new EditorComponent(nodeEditor);
	}

	private class EditorComponent extends Pane implements NodeEditorComponent {
		private static final long serialVersionUID = 1L;
		private final NodeEditor nodeEditor;
//		private final JComboBox fontComboBox;

		/**
		 * @param nodeEditor
		 */
		public EditorComponent(final NodeEditor nodeEditor) {
			this.nodeEditor = nodeEditor;
//			setLayout(new FlowLayout(FlowLayout.CENTER));
//			fontComboBox = GUIFactory.createComboBox(new FontComboBoxModel(), null);
//			fontComboBox.setRenderer(new FontListCellRenderer());
//			fontComboBox.setSelectedItem(((FontElementNodeValue) nodeEditor.getNodeValue()).getValue().getFamily());
//			fontComboBox.addActionListener(new NodeEditorActionListener(nodeEditor));
//			this.add(GUIFactory.createLabel(nodeEditor.getNodeLabel(), SwingConstants.CENTER));
//			this.add(fontComboBox);
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.ui.javafx.NodeEditorComponent#getComponent()
		 */
		@Override
		public Node getComponent() {
			return this;
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.ui.javafx.NodeEditorComponent#reloadValue()
		 */
		@Override
		public void reloadValue() {
//			if (nodeEditor.getNodeValue() != null) {
//				fontComboBox.setSelectedItem(((FontElementNodeValue) nodeEditor.getNodeValue()).getValue().getFamily());
//			}
		}

		/**
		 * @see com.nextbreakpoint.nextfractal.core.ui.javafx.NodeEditorComponent#dispose()
		 */
		@Override
		public void dispose() {
		}
	}

//	private class NodeEditorActionListener implements ActionListener {
//		private final NodeEditor nodeEditor;
//
//		/**
//		 * @param nodeEditor
//		 */
//		public NodeEditorActionListener(final NodeEditor nodeEditor) {
//			this.nodeEditor = nodeEditor;
//		}
//
//		/**
//		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
//		 */
//		@Override
//		public void actionPerformed(final ActionEvent e) {
//			final String fontName = (String) ((JComboBox) e.getSource()).getSelectedItem();
//			Font font = new Font(fontName, Font.PLAIN, 12);
//			if (!font.equals(nodeEditor.getNodeValue().getValue())) {
//				nodeEditor.setNodeValue(new FontElementNodeValue(font));
//			}
//		}
//	}
//
//	private class FontComboBoxModel extends DefaultComboBoxModel {
//		private static final long serialVersionUID = 1L;
//
//		public FontComboBoxModel() {
//			String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
//			for (String fontName : fontNames) {
//				addElement(fontName);
//			}
//		}
//	}
//
//	private class FontListCellRenderer extends DefaultListCellRenderer {
//		private static final long serialVersionUID = 1L;
//
//		/**
//		 * @see javax.javafx.DefaultListCellRenderer#getListCellRendererComponent(javax.javafx.JList, java.lang.Object, int, boolean, boolean)
//		 */
//		@Override
//		public Component getListCellRendererComponent(final JList list, final Object value, final int index, final boolean isSelected, final boolean cellHasFocus) {
//			return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
//		}
//	}
}

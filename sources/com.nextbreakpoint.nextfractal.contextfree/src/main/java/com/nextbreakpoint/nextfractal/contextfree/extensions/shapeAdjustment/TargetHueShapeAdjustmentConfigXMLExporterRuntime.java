/*
 * $Id:$
 *
 */
package com.nextbreakpoint.nextfractal.contextfree.extensions.shapeAdjustment;

import org.w3c.dom.Element;

import com.nextbreakpoint.nextfractal.core.elements.FloatElementXMLExporter;
import com.nextbreakpoint.nextfractal.core.extensionPoints.extensionConfigXMLExporter.ExtensionConfigXMLExporterExtensionRuntime;
import com.nextbreakpoint.nextfractal.core.runtime.extension.ExtensionException;
import com.nextbreakpoint.nextfractal.core.runtime.xml.XMLExportException;
import com.nextbreakpoint.nextfractal.core.runtime.xml.XMLExporter;
import com.nextbreakpoint.nextfractal.core.runtime.xml.XMLNodeBuilder;

/**
 * @author Andrea Medeghini
 */
public class TargetHueShapeAdjustmentConfigXMLExporterRuntime extends ExtensionConfigXMLExporterExtensionRuntime {
	/**
	 * @see com.nextbreakpoint.nextfractal.core.extensionPoints.extensionConfigXMLExporter.ExtensionConfigXMLExporterExtensionRuntime#createXMLExporter()
	 */
	@Override
	public XMLExporter<TargetHueShapeAdjustmentConfig> createXMLExporter() {
		return new TargetHueShapeAdjustmentConfigXMLExporter();
	}

	private class TargetHueShapeAdjustmentConfigXMLExporter extends XMLExporter<TargetHueShapeAdjustmentConfig> {
		protected String getConfigElementClassId() {
			return "TargetHueShapeAdjustmentConfig";
		}
		
		/**
		 * @see com.nextbreakpoint.nextfractal.core.runtime.xml.XMLExporter#exportToElement(java.lang.Object, com.nextbreakpoint.nextfractal.core.runtime.xml.XMLNodeBuilder)
		 */
		@Override
		public Element exportToElement(final TargetHueShapeAdjustmentConfig extensionConfig, final XMLNodeBuilder builder) throws XMLExportException {
			final Element element = this.createElement(builder, this.getConfigElementClassId());
			try {
				exportProperties(extensionConfig, element, builder);
			}
			catch (final ExtensionException e) {
				throw new XMLExportException(e);
			}
			return element;
		}
	
		/**
		 * @see com.nextbreakpoint.nextfractal.core.elements.ConfigurableExtensionReferenceElementXMLExporter#exportProperties(com.nextbreakpoint.nextfractal.twister.util.ConfigurableExtensionConfigElement, org.w3c.dom.Element, com.nextbreakpoint.nextfractal.core.runtime.xml.XMLNodeBuilder, java.lang.String)
		 */
		protected void exportProperties(final TargetHueShapeAdjustmentConfig extensionConfig, final Element element, final XMLNodeBuilder builder) throws ExtensionException, XMLExportException {
			exportValue(extensionConfig, createProperty(builder, element, "value"), builder);
		}
	
		private void exportValue(final TargetHueShapeAdjustmentConfig extensionConfig, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
			element.appendChild(new FloatElementXMLExporter().exportToElement(extensionConfig.getValueElement(), builder));
		}
	}
}

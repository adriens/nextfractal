/*
 * $Id:$
 *
 */
package com.nextbreakpoint.nextfractal.contextfree.pathReplacement;

import org.w3c.dom.Element;

import com.nextbreakpoint.nextfractal.contextfree.extensionPoints.pathReplacement.PathReplacementExtensionConfig;
import com.nextbreakpoint.nextfractal.core.elements.ConfigurableExtensionReferenceElementXMLExporter;
import com.nextbreakpoint.nextfractal.core.runtime.extension.ExtensionException;
import com.nextbreakpoint.nextfractal.core.runtime.xml.XMLExportException;
import com.nextbreakpoint.nextfractal.core.runtime.xml.XMLExporter;
import com.nextbreakpoint.nextfractal.core.runtime.xml.XMLNodeBuilder;

/**
 * @author Andrea Medeghini
 */
public class PathReplacementConfigElementXMLExporter extends XMLExporter<PathReplacementConfigElement> {
	/**
	 * @see com.nextbreakpoint.nextfractal.core.runtime.xml.XMLExporter#exportToElement(java.lang.Object, com.nextbreakpoint.nextfractal.core.runtime.xml.XMLNodeBuilder)
	 */
	@Override
	public Element exportToElement(final PathReplacementConfigElement configElement, final XMLNodeBuilder builder) throws XMLExportException {
		final Element element = this.createElement(builder, PathReplacementConfigElement.CLASS_ID);
		try {
			exportProperties(configElement, element, builder);
		}
		catch (final ExtensionException e) {
			throw new XMLExportException(e);
		}
		return element;
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.elements.ConfigurableExtensionReferenceElementXMLExporter#exportProperties(com.nextbreakpoint.nextfractal.twister.util.ConfigurableExtensionConfigElement, org.w3c.dom.Element, com.nextbreakpoint.nextfractal.core.runtime.xml.XMLNodeBuilder, java.lang.String)
	 */
	protected void exportProperties(final PathReplacementConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws ExtensionException, XMLExportException {
		exportExtension(configElement, createProperty(builder, element, "extension"), builder);
	}

	private void exportExtension(final PathReplacementConfigElement configElement, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		element.appendChild(new ConfigurableExtensionReferenceElementXMLExporter<PathReplacementExtensionConfig>().exportToElement(configElement.getExtensionElement(), builder));
	}
}

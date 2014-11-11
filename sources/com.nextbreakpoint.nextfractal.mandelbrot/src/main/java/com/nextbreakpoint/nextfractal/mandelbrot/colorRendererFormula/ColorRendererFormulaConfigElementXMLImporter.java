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
package com.nextbreakpoint.nextfractal.mandelbrot.colorRendererFormula;

import java.util.List;

import org.w3c.dom.Element;

import com.nextbreakpoint.nextfractal.core.elements.ExtensionReferenceElement;
import com.nextbreakpoint.nextfractal.core.elements.ExtensionReferenceElementXMLImporter;
import com.nextbreakpoint.nextfractal.core.runtime.extension.ExtensionException;
import com.nextbreakpoint.nextfractal.core.runtime.xml.XMLImportException;
import com.nextbreakpoint.nextfractal.core.runtime.xml.XMLImporter;
import com.nextbreakpoint.nextfractal.mandelbrot.MandelbrotRegistry;

/**
 * @author Andrea Medeghini
 */
public class ColorRendererFormulaConfigElementXMLImporter extends XMLImporter<ColorRendererFormulaConfigElement> {
	/**
	 * @see com.nextbreakpoint.nextfractal.core.runtime.xml.XMLImporter#importFromElement(org.w3c.dom.Element)
	 */
	@Override
	public ColorRendererFormulaConfigElement importFromElement(final Element element) throws XMLImportException {
		checkClassId(element, ColorRendererFormulaConfigElement.CLASS_ID);
		final ColorRendererFormulaConfigElement configElement = new ColorRendererFormulaConfigElement();
		final List<Element> propertyElements = getProperties(element);
		if (propertyElements.size() == 1) {
			try {
				importProperties(configElement, propertyElements);
			}
			catch (final ExtensionException e) {
				throw new XMLImportException(e);
			}
		}
		return configElement;
	}

	/**
	 * @param configElement
	 * @param propertyElements
	 * @throws ExtensionException
	 * @throws XMLImportException
	 */
	protected void importProperties(final ColorRendererFormulaConfigElement configElement, final List<Element> propertyElements) throws ExtensionException, XMLImportException {
		importExtension(configElement, propertyElements.get(0));
	}

	private void importExtension(final ColorRendererFormulaConfigElement configElement, final Element element) throws XMLImportException {
		final List<Element> extensionElements = this.getElements(element, ExtensionReferenceElement.CLASS_ID);
		if (extensionElements.size() == 1) {
			configElement.setReference(new ExtensionReferenceElementXMLImporter(MandelbrotRegistry.getInstance().getColorRendererFormulaRegistry()).importFromElement(extensionElements.get(0)).getReference());
		}
	}
}

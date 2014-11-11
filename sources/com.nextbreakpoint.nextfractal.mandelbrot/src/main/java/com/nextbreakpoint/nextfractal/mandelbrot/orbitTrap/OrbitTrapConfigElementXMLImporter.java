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
package com.nextbreakpoint.nextfractal.mandelbrot.orbitTrap;

import java.util.List;

import org.w3c.dom.Element;

import com.nextbreakpoint.nextfractal.core.elements.ComplexElement;
import com.nextbreakpoint.nextfractal.core.elements.ComplexElementXMLImporter;
import com.nextbreakpoint.nextfractal.core.elements.ConfigurableExtensionReferenceElement;
import com.nextbreakpoint.nextfractal.core.elements.ConfigurableExtensionReferenceElementXMLImporter;
import com.nextbreakpoint.nextfractal.core.runtime.extension.ExtensionException;
import com.nextbreakpoint.nextfractal.core.runtime.xml.XMLImportException;
import com.nextbreakpoint.nextfractal.core.runtime.xml.XMLImporter;
import com.nextbreakpoint.nextfractal.mandelbrot.MandelbrotRegistry;
import com.nextbreakpoint.nextfractal.mandelbrot.orbitTrap.extension.OrbitTrapExtensionConfig;

/**
 * @author Andrea Medeghini
 */
public class OrbitTrapConfigElementXMLImporter extends XMLImporter<OrbitTrapConfigElement> {
	/**
	 * @see com.nextbreakpoint.nextfractal.core.runtime.xml.XMLImporter#importFromElement(org.w3c.dom.Element)
	 */
	@Override
	public OrbitTrapConfigElement importFromElement(final Element element) throws XMLImportException {
		checkClassId(element, OrbitTrapConfigElement.CLASS_ID);
		final OrbitTrapConfigElement configElement = new OrbitTrapConfigElement();
		final List<Element> propertyElements = getProperties(element);
		try {
			importProperties(configElement, propertyElements);
		}
		catch (final ExtensionException e) {
			throw new XMLImportException(e);
		}
		return configElement;
	}

	/**
	 * @param configElement
	 * @param propertyElements
	 * @throws ExtensionException
	 * @throws XMLImportException
	 */
	protected void importProperties(final OrbitTrapConfigElement configElement, final List<Element> propertyElements) throws ExtensionException, XMLImportException {
		importExtension(configElement, propertyElements.get(0));
		importCenter(configElement, propertyElements.get(1));
	}

	private void importExtension(final OrbitTrapConfigElement configElement, final Element element) throws XMLImportException {
		final List<Element> extensionElements = this.getElements(element, ConfigurableExtensionReferenceElement.CLASS_ID);
		if (extensionElements.size() == 1) {
			configElement.setReference(new ConfigurableExtensionReferenceElementXMLImporter<OrbitTrapExtensionConfig>(MandelbrotRegistry.getInstance().getOrbitTrapRegistry()).importFromElement(extensionElements.get(0)).getReference());
		}
	}

	private void importCenter(final OrbitTrapConfigElement configElement, final Element element) throws XMLImportException {
		final List<Element> complexElements = this.getElements(element, ComplexElement.CLASS_ID);
		if (complexElements.size() == 1) {
			configElement.setCenter(new ComplexElementXMLImporter().importFromElement(complexElements.get(0)).getValue());
		}
	}
}

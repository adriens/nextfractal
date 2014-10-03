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
package com.nextbreakpoint.nextfractal.twister.extensions.action;

import org.w3c.dom.Element;

import com.nextbreakpoint.nextfractal.core.tree.NodeActionValue;
import com.nextbreakpoint.nextfractal.core.util.AbstractActionXMLExporterRuntime;
import com.nextbreakpoint.nextfractal.core.xml.XMLExportException;
import com.nextbreakpoint.nextfractal.core.xml.XMLNodeBuilder;
import com.nextbreakpoint.nextfractal.twister.common.SpeedElement;
import com.nextbreakpoint.nextfractal.twister.common.SpeedElementXMLExporter;
import com.nextbreakpoint.nextfractal.twister.util.Speed;

/**
 * @author Andrea Medeghini
 */
public class SpeedNodeActionXMLExporterRuntime extends AbstractActionXMLExporterRuntime {
	/**
	 * @see com.nextbreakpoint.nextfractal.core.util.AbstractActionXMLExporterRuntime#exportParams(com.nextbreakpoint.nextfractal.core.tree.NodeActionValue, org.w3c.dom.Element, com.nextbreakpoint.nextfractal.core.xml.XMLNodeBuilder)
	 */
	@Override
	protected void exportParams(final NodeActionValue action, final Element element, final XMLNodeBuilder builder) throws XMLExportException {
		final SpeedElementXMLExporter exporter = new SpeedElementXMLExporter();
		final SpeedElement configElement0 = new SpeedElement((Speed) action.getActionParams()[0]);
		final SpeedElement configElement1 = new SpeedElement((Speed) action.getActionParams()[1]);
		element.appendChild(exporter.exportToElement(configElement0, builder));
		element.appendChild(exporter.exportToElement(configElement1, builder));
	}
}

/*
 * $Id:$
 *
 */
package com.nextbreakpoint.nextfractal.contextfree.elements;

import com.nextbreakpoint.nextfractal.core.runtime.ValueConfigElementXMLExporter;

/**
 * @author Andrea Medeghini
 */
public class StrokeCapElementXMLExporter extends ValueConfigElementXMLExporter<String, StrokeCapElement> {
	/**
	 * @see com.nextbreakpoint.nextfractal.core.runtime.ValueConfigElementXMLExporter#formatValue(java.io.Serializable)
	 */
	@Override
	protected String formatValue(final String value) {
		return value;
	}
}

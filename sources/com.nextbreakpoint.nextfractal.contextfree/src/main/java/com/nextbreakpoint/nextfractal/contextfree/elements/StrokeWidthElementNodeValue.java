/*
 * $Id:$
 *
 */
package com.nextbreakpoint.nextfractal.contextfree.elements;

import com.nextbreakpoint.nextfractal.core.runtime.model.NodeValue;

/**
 * @author Andrea Medeghini
 */
public class StrokeWidthElementNodeValue extends NodeValue<Float> {
	private static final long serialVersionUID = 1L;

	/**
	 * @param value
	 */
	public StrokeWidthElementNodeValue(final Float value) {
		super(value);
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.runtime.model.NodeValue#getValueClone()
	 */
	@Override
	public Float getValueClone() {
		return getValue();
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.runtime.model.NodeValue#clone()
	 */
	@Override
	public StrokeWidthElementNodeValue clone() {
		return new StrokeWidthElementNodeValue(getValueClone());
	}
}
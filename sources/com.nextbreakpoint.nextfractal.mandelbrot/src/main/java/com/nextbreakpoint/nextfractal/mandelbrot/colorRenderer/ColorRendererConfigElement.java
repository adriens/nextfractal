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
package com.nextbreakpoint.nextfractal.mandelbrot.colorRenderer;

import com.nextbreakpoint.nextfractal.core.elements.ConfigurableExtensionReferenceElement;
import com.nextbreakpoint.nextfractal.core.runtime.AbstractConfigElement;
import com.nextbreakpoint.nextfractal.core.runtime.ConfigContext;
import com.nextbreakpoint.nextfractal.core.runtime.ConfigElement;
import com.nextbreakpoint.nextfractal.core.runtime.extension.ConfigurableExtensionReference;
import com.nextbreakpoint.nextfractal.mandelbrot.extensionPoints.colorRenderer.ColorRendererExtensionConfig;

/**
 * @author Andrea Medeghini
 */
public class ColorRendererConfigElement extends AbstractConfigElement {
	private static final long serialVersionUID = 1L;
	public static final String CLASS_ID = "ColorRenderer";
	private final ConfigurableExtensionReferenceElement<ColorRendererExtensionConfig> extensionElement = new ConfigurableExtensionReferenceElement<ColorRendererExtensionConfig>();

	/**
	 * Constructs a new element.
	 */
	public ColorRendererConfigElement() {
		super(ColorRendererConfigElement.CLASS_ID);
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.elements.ConfigurableExtensionReferenceElement#setContext(com.nextbreakpoint.nextfractal.core.runtime.ConfigContext)
	 */
	@Override
	public void setContext(final ConfigContext context) {
		super.setContext(context);
		extensionElement.setContext(context);
	}

	/**
	 * @return
	 */
	@Override
	public ColorRendererConfigElement clone() {
		final ColorRendererConfigElement element = new ColorRendererConfigElement();
		if (getReference() != null) {
			element.setReference(getReference().clone());
		}
		return element;
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.runtime.ConfigElement#copyFrom(com.nextbreakpoint.nextfractal.core.runtime.ConfigElement)
	 */
	@Override
	public void copyFrom(ConfigElement source) {
		ColorRendererConfigElement element = (ColorRendererConfigElement) source;
		if (element.getReference() != null) {
			setReference(element.getReference().clone());
		}
	}

	/**
	 * @return
	 */
	public ConfigurableExtensionReference<ColorRendererExtensionConfig> getReference() {
		return extensionElement.getReference();
	}

	/**
	 * @param reference
	 */
	public void setReference(final ConfigurableExtensionReference<ColorRendererExtensionConfig> reference) {
		extensionElement.setReference(reference);
	}

	/**
	 * @return
	 */
	public ConfigurableExtensionReferenceElement<ColorRendererExtensionConfig> getExtensionElement() {
		return extensionElement;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		final ColorRendererConfigElement other = (ColorRendererConfigElement) obj;
		if (extensionElement == null) {
			if (other.extensionElement != null) {
				return false;
			}
		}
		else if (!extensionElement.equals(other.extensionElement)) {
			return false;
		}
		return true;
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.runtime.AbstractConfigElement#dispose()
	 */
	@Override
	public void dispose() {
		extensionElement.dispose();
		super.dispose();
	}
}

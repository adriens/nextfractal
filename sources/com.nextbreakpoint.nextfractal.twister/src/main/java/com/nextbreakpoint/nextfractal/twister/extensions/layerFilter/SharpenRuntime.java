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
package com.nextbreakpoint.nextfractal.twister.extensions.layerFilter;

import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

import com.nextbreakpoint.nextfractal.core.util.Surface;
import com.nextbreakpoint.nextfractal.core.util.Tile;
import com.nextbreakpoint.nextfractal.twister.layerFilter.extension.LayerFilterExtensionRuntime;
import com.nextbreakpoint.nextfractal.twister.util.Padding;

/**
 * @author Andrea Medeghini
 */
public class SharpenRuntime extends LayerFilterExtensionRuntime<SharpenConfig> {
	private final float[] kernel = { -1.0f, -1.0f, -1.0f, -1.0f, 9.0f, -1.0f, -1.0f, -1.0f, -1.0f };
	private final ConvolveOp convolveop = new ConvolveOp(new Kernel(3, 3, kernel), ConvolveOp.EDGE_NO_OP, null);
	private final Padding padding = new Padding(0, 0, 0, 0);

	/**
	 * @see com.nextbreakpoint.nextfractal.twister.layerFilter.extension.LayerFilterExtensionRuntime#getPadding()
	 */
	@Override
	public Padding getPadding() {
		return padding;
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.twister.layerFilter.extension.LayerFilterExtensionRuntime#renderImage(com.nextbreakpoint.nextfractal.core.util.Surface, com.nextbreakpoint.nextfractal.core.util.Surface)
	 */
	@Override
	public void renderImage(final Surface src, final Surface dst) {
		convolveop.filter(src.getImage(), dst.getImage());
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.twister.layerFilter.extension.LayerFilterExtensionRuntime#setTile(com.nextbreakpoint.nextfractal.core.util.Tile)
	 */
	@Override
	public void setTile(final Tile tile) {
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.twister.layerFilter.extension.LayerFilterExtensionRuntime#prepareFilter()
	 */
	@Override
	public void prepareFilter() {
	}
}

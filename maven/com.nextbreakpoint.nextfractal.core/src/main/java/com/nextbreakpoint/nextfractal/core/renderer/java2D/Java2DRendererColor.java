/*
 * NextFractal 1.0
 * https://github.com/nextbreakpoint/nextfractal
 *
 * Copyright 2015 Andrea Medeghini
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
package com.nextbreakpoint.nextfractal.core.renderer.java2D;

import java.awt.Color;

import com.nextbreakpoint.nextfractal.core.renderer.RendererColor;
import com.nextbreakpoint.nextfractal.core.renderer.RendererGraphicsContext;

public class Java2DRendererColor implements RendererColor {
	private Color color;
	
	public Java2DRendererColor(double red, double green, double blue, double opacity) {
		color = new Color((float)red, (float)green, (float)blue, (float)opacity);
	}

	@Override
	public void setStroke(RendererGraphicsContext context) {
		((Java2DRendererGraphicsContext)context).getGraphicsContext().setColor(color);
	}
	
	@Override
	public void setFill(RendererGraphicsContext context) {
		((Java2DRendererGraphicsContext)context).getGraphicsContext().setColor(color);
	}
}
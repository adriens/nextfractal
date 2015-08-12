/*
 * NextFractal 1.1.5
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

import java.awt.Font;

import com.nextbreakpoint.nextfractal.core.renderer.RendererFont;
import com.nextbreakpoint.nextfractal.core.renderer.RendererGraphicsContext;

public class Java2DRendererFont implements RendererFont {
	private Font font;
	
	@Override
	public void setFont(RendererGraphicsContext context) {
		((Java2DRendererGraphicsContext)context).getGraphicsContext().setFont(font);
	}
}

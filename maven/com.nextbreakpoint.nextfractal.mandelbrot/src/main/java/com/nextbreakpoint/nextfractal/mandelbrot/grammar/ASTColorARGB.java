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
package com.nextbreakpoint.nextfractal.mandelbrot.grammar;

import java.text.DecimalFormat;

import com.nextbreakpoint.nextfractal.core.utils.Colors;

public class ASTColorARGB {
	private static final DecimalFormat format = new DecimalFormat("0.##");
	private final float[] components;

	public ASTColorARGB(int argb) {
		components = Colors.color(argb);
	}

	public ASTColorARGB(float a, float r, float g, float b) {
		components = new float[] { a, r, g, b };
	}

	public float[] getComponents() {
		return components;
	}

	public int getARGB() {
		return Colors.toARGB(components);
	}

	@Override
	public String toString() {
		return "(" + format.format(components[0]) + "," + format.format(components[1]) + "," + format.format(components[2]) + "," + format.format(components[3]) + ")";
	}
}
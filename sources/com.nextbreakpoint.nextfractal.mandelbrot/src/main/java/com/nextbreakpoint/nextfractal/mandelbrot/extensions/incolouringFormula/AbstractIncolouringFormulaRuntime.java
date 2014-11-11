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
package com.nextbreakpoint.nextfractal.mandelbrot.extensions.incolouringFormula;

import com.nextbreakpoint.nextfractal.mandelbrot.extensionPoints.incolouringFormula.IncolouringFormulaExtensionConfig;
import com.nextbreakpoint.nextfractal.mandelbrot.extensionPoints.incolouringFormula.IncolouringFormulaExtensionRuntime;
import com.nextbreakpoint.nextfractal.mandelbrot.extensionPoints.renderingFormula.RenderingFormulaExtensionRuntime;

/**
 * @author Andrea Medeghini
 */
public abstract class AbstractIncolouringFormulaRuntime<T extends IncolouringFormulaExtensionConfig> extends IncolouringFormulaExtensionRuntime<T> {
	/**
	 * @see com.nextbreakpoint.nextfractal.mandelbrot.extensionPoints.incolouringFormula.IncolouringFormulaExtensionRuntime#isHorizontalSymetryAllowed()
	 */
	@Override
	public boolean isHorizontalSymetryAllowed() {
		return false;
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.mandelbrot.extensionPoints.incolouringFormula.IncolouringFormulaExtensionRuntime#isVerticalSymetryAllowed()
	 */
	@Override
	public boolean isVerticalSymetryAllowed() {
		return false;
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.mandelbrot.extensionPoints.incolouringFormula.IncolouringFormulaExtensionRuntime#isSolidGuessAllowed()
	 */
	@Override
	public boolean isSolidGuessAllowed() {
		return false;
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.mandelbrot.extensionPoints.incolouringFormula.IncolouringFormulaExtensionRuntime#prepareForRendering(int)
	 */
	@Override
	public void prepareForRendering(final RenderingFormulaExtensionRuntime<?> formulaRuntime, final int maxColors) {
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.core.runtime.extension.ExtensionRuntime#dispose()
	 */
	@Override
	public void dispose() {
	}
}

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
package com.nextbreakpoint.nextfractal.flux.mandelbrot.renderer;

import com.nextbreakpoint.nextfractal.flux.mandelbrot.MutableNumber;
import com.nextbreakpoint.nextfractal.flux.mandelbrot.renderer.Renderer;

/**
 * @author Andrea Medeghini
 */
public class Renderer {
	public static final int MODE_CALCULATE = 0x01;
	public static final int MODE_REFRESH = 0x02;
	protected final RendererStrategy rendererStrategy;
	protected final RendererData rendererData;
	protected boolean isAborted;
	protected float progress;
	protected int width;
	protected int height;

	/**
	 * @param threadPriority
	 */
	public Renderer(RendererStrategy renderingStrategy, RendererData renderedData, int width, int height) {
		this.rendererStrategy = renderingStrategy;
		this.rendererData = renderedData;
		this.width = width;
		this.height = height;
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.flux.mandelbrot.MandelbrotManager.core.fractal.renderer.AbstractFractalRenderer#free()
	 */
	protected void free() {
		rendererData.free();
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.flux.mandelbrot.MandelbrotManager.core.fractal.renderer.AbstractFractalRenderer#init()
	 */
	protected void init() {
		rendererData.init(width, height);
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.flux.mandelbrot.MandelbrotManager.renderer.AbstractMandelbrotRenderer#doRender(boolean)
	 */
	protected void doRender(final boolean dynamic) {
		progress = 0;
		update();
		rendererStrategy.updateParameters();
		final MutableNumber px = new MutableNumber(0, 0);
		final MutableNumber pw = new MutableNumber(0, 0);
		final RendererPoint p = new RendererPoint();
		rendererData.initPositions();
		int offset = 0;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				px.set(rendererData.point());
				pw.set(rendererData.positionX(x), rendererData.positionY(y));
				p.pr = rendererData.positionX(x);
				p.pi = rendererData.positionY(y);
				rendererData.setPixel(offset, rendererStrategy.renderPoint(p, px, pw));
				offset += 1;
			}
			if (y % 20 == 0) {
				progress = (float)y / (float)height;
				rendererData.copy();
			}
			Thread.yield();
			if (isInterrupted()) {
				isAborted = true;
				break;
			}
		}
		rendererData.copy();
		if (isAborted) {
			progress = 1;
		}
	}

	private void update() {
		// TODO Auto-generated method stub
		
	}

	public boolean isInterrupted() {
		// TODO Auto-generated method stub
		return isAborted || Thread.currentThread().isInterrupted();
	}

	public void start() {
		// TODO Auto-generated method stub
		
	}

	public void abort() {
		// TODO Auto-generated method stub
		
	}

	public void join() {
		// TODO Auto-generated method stub
		
	}

	public void dispose() {
		// TODO Auto-generated method stub
	}

	public float getProgress() {
		return progress;
	}
}
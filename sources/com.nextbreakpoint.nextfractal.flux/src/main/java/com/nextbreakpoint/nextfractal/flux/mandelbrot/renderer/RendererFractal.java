package com.nextbreakpoint.nextfractal.flux.mandelbrot.renderer;

public interface RendererFractal {
	public Number[] renderOrbit(Number x, Number y);

	public float[] renderColor(Number[] o);
}
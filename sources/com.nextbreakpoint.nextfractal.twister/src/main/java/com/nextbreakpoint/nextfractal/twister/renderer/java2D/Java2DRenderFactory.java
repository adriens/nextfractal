package com.nextbreakpoint.nextfractal.twister.renderer.java2D;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import com.nextbreakpoint.nextfractal.twister.renderer.RenderAffine;
import com.nextbreakpoint.nextfractal.twister.renderer.RenderBuffer;
import com.nextbreakpoint.nextfractal.twister.renderer.RenderColor;
import com.nextbreakpoint.nextfractal.twister.renderer.RenderFactory;
import com.nextbreakpoint.nextfractal.twister.renderer.RenderGraphicsContext;

public class Java2DRenderFactory implements RenderFactory {
	/**
	 * @see com.nextbreakpoint.nextfractal.twister.renderer.RenderFactory#createBuffer(int, int)
	 */
	@Override
	public RenderBuffer createBuffer(int widh, int height) {
		return new Java2DRenderBuffer(widh, height);
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.twister.renderer.RenderFactory#createGraphicsContext(java.lang.Object)
	 */
	@Override
	public RenderGraphicsContext createGraphicsContext(Object context) {
		return new Java2DRenderGraphicsContext((Graphics2D)context);
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.twister.renderer.RenderFactory#createTranslateAffine(double, double)
	 */
	@Override
	public RenderAffine createTranslateAffine(double x, double y) {
		return new Java2DRenderAffine(AffineTransform.getTranslateInstance(x, y));
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.twister.renderer.RenderFactory#createRotateAffine(double, double, double)
	 */
	@Override
	public RenderAffine createRotateAffine(double a, double centerX, double centerY) {
		return new Java2DRenderAffine(AffineTransform.getRotateInstance(a, centerX, centerY));
	}
	
	/**
	 * @see com.nextbreakpoint.nextfractal.twister.renderer.RenderFactory#createAffine()
	 */
	@Override
	public RenderAffine createAffine() {
		return new Java2DRenderAffine(new AffineTransform());
	}

	/**
	 * @see com.nextbreakpoint.nextfractal.twister.renderer.RenderFactory#createColor(int, int, int, int)
	 */
	@Override
	public RenderColor createColor(int red, int green, int blue, int opacity) {
		return new Java2DRenderColor(red, green, blue, opacity);
	}
}
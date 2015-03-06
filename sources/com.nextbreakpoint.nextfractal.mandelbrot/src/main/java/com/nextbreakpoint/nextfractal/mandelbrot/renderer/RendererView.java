package com.nextbreakpoint.nextfractal.mandelbrot.renderer;

import com.nextbreakpoint.nextfractal.core.utils.DoubleVector4D;
import com.nextbreakpoint.nextfractal.core.utils.IntegerVector4D;
import com.nextbreakpoint.nextfractal.mandelbrot.MandelbrotView;
import com.nextbreakpoint.nextfractal.mandelbrot.core.Number;

/**
 * @author Andrea Medeghini
 */
public class RendererView {
	private DoubleVector4D traslation;
	private DoubleVector4D rotation;
	private DoubleVector4D scale;
	private IntegerVector4D state;
	private Number point;
	private boolean julia;
	
	public RendererView() {
		traslation = new DoubleVector4D(0, 0, 1, 0);
		rotation = new DoubleVector4D(0, 0, 0, 0);
		scale = new DoubleVector4D(1, 1, 1, 1);
		state = new IntegerVector4D(0, 0, 0, 0);
		point = new Number(0, 0);
	}

	public RendererView(MandelbrotView view) {
		traslation = new DoubleVector4D(view.getTraslation());
		rotation = new DoubleVector4D(view.getRotation());
		scale = new DoubleVector4D(view.getScale());
		state = new IntegerVector4D(0, 0, 0, 0);
		point = new Number(0, 0);
	}
	
	public DoubleVector4D getTraslation() {
		return traslation;
	}

	public void setTraslation(DoubleVector4D translation) {
		this.traslation = translation;
	}

	public DoubleVector4D getRotation() {
		return rotation;
	}

	public void setRotation(DoubleVector4D rotation) {
		this.rotation = rotation;
	}

	public DoubleVector4D getScale() {
		return scale;
	}

	public void setScale(DoubleVector4D scale) {
		this.scale = scale;
	}

	public Number getPoint() {
		return point;
	}

	public void setPoint(Number point) {
		this.point = point;
	}

	public boolean isJulia() {
		return julia;
	}

	public void setJulia(boolean julia) {
		this.julia = julia;
	}

	public IntegerVector4D getState() {
		return state;
	}

	public void setState(IntegerVector4D state) {
		this.state = state;
	}
}
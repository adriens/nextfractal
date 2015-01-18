package com.nextbreakpoint.nextfractal.mandelbrot.core;

public abstract class Orbit {
	protected Number[] initialRegion = new Number[2];
	protected Number[] region = new Number[2];
	protected Number x = new Number(0,0);
	protected Number w = new Number(0,0);
	protected Number z = new Number(0,0);
	protected Number n = new Number(0);
	protected Number c = new Number(0);
	protected Scope scope;

	public Orbit() {
		initialRegion[0] = new MutableNumber();
		initialRegion[1] = new MutableNumber();
		region[0] = new MutableNumber();
		region[1] = new MutableNumber();
	}

	public Scope getScope() {
		return scope;
	}

	public void setScope(Scope scope) {
		this.scope = scope;
	}

	public void setX(Number x) {
		this.x = x;
	}

	public void setW(Number w) {
		this.w = w;
	}

	public Number getX() {
		return x;
	}

	public Number getW() {
		return w;
	}

	public Number getZ() {
		return z;
	}

	public Number getN() {
		return n;
	}

	public Number getC() {
		return c;
	}

	public Number[] region() {
		return region;
	}

	protected Trap trap(Number center) {
		return new Trap(center);
	}
	
	public abstract void init();

	public abstract void render();

	public void getState(MutableNumber[] state) {
		scope.getState(state);
	}

	public int stateSize() {
		return scope.stateSize();
	}

	public Number getVariable(int index) {
		return scope.getVariable(index);
	}

	public void setVariable(int index, Number value) {
		scope.setVariable(index, value);
	}

	public void createVariable(Number value) {
		scope.createVariable(value);
	}

	public void setRegion(Number[] region) {
		this.region = region;
	}

	public Number[] getInitialRegion() {
		return initialRegion;
	}

	public void setInitialRegion(Number a, Number b) {
		this.initialRegion[0].set(a);
		this.initialRegion[1].set(b);
	}
}
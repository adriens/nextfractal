/*
 * NextFractal 1.2.1
 * https://github.com/nextbreakpoint/nextfractal
 *
 * Copyright 2015-2016 Andrea Medeghini
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
package com.nextbreakpoint.nextfractal.contextfree.grammar;

import org.antlr.v4.runtime.Token;

import java.awt.geom.Point2D;
import java.util.Iterator;
import java.util.List;

public class RTI {
	private CFDG cfdg;
	private int width;
	private int height;
	private double minSize;
	private int variation;
	private double border;
	private Point2D.Double lastPoint;
	private boolean stop;
	private boolean closed;
	private boolean wantMoveTo;
	private boolean wantCommand;
	private boolean opsOnly;
	private int index;
	private int nextIndex;
	private int stackSize;

	public RTI(CFDG cfdg, int width, int height, double minSize, int variation, double border) {
		this.cfdg = cfdg;
		this.width = width;
		this.height = height;
		this.minSize = minSize;
		this.variation = variation;
		this.border = border;
	}

	public void colorConflict(Token location) {
		// TODO rivedere
		warning(location, "Conflicting color change");
	}

	public void init() {
		lastPoint = new Point2D.Double(0, 0);
		stop = false;
		closed = false;
		wantMoveTo = true;
		wantCommand = true;
		opsOnly = false;
		index = 0;
		nextIndex = 0;
	}

	public boolean isNatual(double n) {
		//TODO rivedere
		return n >= 0 && n <= getMaxNatural() && n == Math.floor(n);
	}

	public void initStack(StackRule parameters) {
		if (parameters != null && parameters.getParamCount() > 0) {
			if (stackSize + parameters.getParamCount() > getCFStack().size()) {
				throw new RuntimeException("Maximum stack size exceeded");
			}
			int oldSize = stackSize;
			stackSize += parameters.getParamCount()
		}
	}

	public double getCurrentTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getCurrentFrame() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setRandUsed(boolean b) {
		// TODO Auto-generated method stub
		
	}

	public Rand64 getCurrentSeed() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getMaxNatural() {
		// TODO Auto-generated method stub
		return 0;
	}

	public StackType stackItem(int stackIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	public StackType getLogicalStackTop() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<StackType> getCFStack() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setLogicalStackTop(StackType stackType) {
		// TODO Auto-generated method stub
		
	}

	public boolean getRequestStop() {
		// TODO Auto-generated method stub
		return false;
	}

	public void processShape(Shape shape) {
		// TODO Auto-generated method stub
		
	}

	public void processSubpath(Shape shape, boolean tr, ERepElemType repType) {
		// TODO Auto-generated method stub
		
	}

	public void unwindStack(int size, List<ASTParameter> parameters) {
		// TODO Auto-generated method stub
		
	}

	public void setCurrentSeed(Rand64 seed) {
		// TODO Auto-generated method stub
		
	}

	public void processPrimShape(Shape parent, ASTRule astRule) {
		// TODO Auto-generated method stub
		
	}

	public ASTCompiledPath getCurrentPath() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setCurrentPath(ASTCompiledPath path) {
		// TODO Auto-generated method stub
		
	}

	public void setCurrentCommand(Iterator<CommandInfo> iterator) {

	}

	public boolean isRandUsed() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setMaxShapes(double v) {
	}

	public void initBounds() {
	}

	private void warning(Token location, String message) {
		//TODO completare
		System.out.println(message);
	}
}

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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.antlr.v4.runtime.Token;

import com.nextbreakpoint.nextfractal.mandelbrot.compiler.CompilerVariable;

public class ASTFractal extends ASTObject {
	private Map<String, CompilerVariable> vars = new HashMap<>();
	private ASTOrbit orbit;
	private ASTColor color;

	public ASTFractal(Token location) {
		super(location);
		registerVariable("x", false, false, location);
		registerVariable("w", false, false, location);
		registerVariable("z", false, false, location);
		registerVariable("n", false, false, location);
		registerVariable("c", false, false, location);
	}

	public void registerStateVariable(String varName, Token location) {
		if (vars.get(varName) == null) {
			registerVariable(varName, false, true, location);
		}
		if (orbit == null) {
			throw new ASTException("Orbit not defined", location);
		}
		orbit.addVariable(varName);
	}

	public ASTOrbit getOrbit() {
		return orbit;
	}
	
	public void setOrbit(ASTOrbit orbit) {
		this.orbit = orbit;
	}
	
	public ASTColor getColor() {
		return color;
	}
	
	public void setColor(ASTColor color) {
		this.color = color;
		if (orbit != null && color != null) {
			color.setVariables(orbit.getVariables());
		}
	}

	public void registerVariable(String name, boolean real, boolean create, Token location) {
		CompilerVariable var = vars.get(name);
		if (var == null) {
			var = new CompilerVariable(name, real, create);
			vars.put(var.getName(), var);
		} else if (!real && var.isReal()) {
			throw new ASTException("Expression not assignable: " + location.getText(), location);
		}
	}

	public CompilerVariable getVariable(String name, Token location) {
		CompilerVariable var = vars.get(name);
		if (var == null) {
			throw new ASTException("Variable not defined: " + location.getText(), location);
		}
		return var;
	}

	public Collection<CompilerVariable> getVars() {
		return vars.values();
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("orbit = {");
		builder.append(orbit);
		builder.append("},color = {");
		builder.append(color);
		builder.append("}");
		return builder.toString();
	}
}

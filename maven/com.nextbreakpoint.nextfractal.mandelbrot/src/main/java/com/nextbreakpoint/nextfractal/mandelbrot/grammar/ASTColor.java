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

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.Token;

public class ASTColor extends ASTObject {
	private List<ASTPalette> palettes = new ArrayList<>(); 
	private List<ASTRule> rules = new ArrayList<>(); 
	private List<String> variables = new ArrayList<>();
	private ASTColorARGB argb; 

	public ASTColor(Token location, ASTColorARGB argb) {
		super(location);
		this.argb = argb;
	}

	public List<ASTPalette> getPalettes() {
		return palettes;
	}

	public void addPalette(ASTPalette palette) {
		palettes.add(palette);
	}
	
	public List<ASTRule> getRules() {
		return rules;
	}

	public void addRule(ASTRule rule) {
		rules.add(rule);
	}

	public ASTColorARGB getArgb() {
		return argb;
	}

	public void setArgb(ASTColorARGB argb) {
		this.argb = argb;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("argb = ");
		builder.append(argb);
		builder.append(",palettes = [");
		for (int i = 0; i < palettes.size(); i++) {
			ASTPalette palette = palettes.get(i);
			builder.append("{");
			builder.append(palette);
			builder.append("}");
			if (i < palettes.size() - 1) {
				builder.append(",");
			}
		}
		builder.append("]");
		builder.append(",rules = [");
		for (int i = 0; i < rules.size(); i++) {
			ASTRule rule = rules.get(i);
			builder.append("{");
			builder.append(rule);
			builder.append("}");
			if (i < rules.size() - 1) {
				builder.append(",");
			}
		}
		builder.append("]");
		return builder.toString();
	}

	public List<String> getVariables() {
		return variables;
	}

	public void setVariables(List<String> variables) {
		this.variables = variables;
	}
}
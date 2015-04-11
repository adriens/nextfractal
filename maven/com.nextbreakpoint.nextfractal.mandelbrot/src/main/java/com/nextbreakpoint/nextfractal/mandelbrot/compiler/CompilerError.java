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
package com.nextbreakpoint.nextfractal.mandelbrot.compiler;

public class CompilerError {
	private ErrorType type;
	private long line;
	private long charPositionInLine;
	private long index;
	private long length;
	private String message;

	public CompilerError(ErrorType type, long line, long charPositionInLine, long index, long length, String message) {
		this.type = type;
		this.line = line;
		this.index = index;
		this.charPositionInLine = charPositionInLine;
		this.message = message;
	}

	public ErrorType getType() {
		return type;
	}

	public long getLine() {
		return line;
	}

	public long getCharPositionInLine() {
		return charPositionInLine;
	}

	public String getMessage() {
		return message;
	}
	
	public long getLength() {
		return length;
	}
	
	public long getIndex() {
		return index;
	}

	@Override
	public String toString() {
		return "[" + line + ":" + charPositionInLine + ":" + index + ":" + length + "] " + message;
	}

	public static enum ErrorType {
		M_COMPILER, JAVA_COMPILER;
	}
}
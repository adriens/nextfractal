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
package com.nextbreakpoint.nextfractal.contextfree.grammar.ast;

import com.nextbreakpoint.nextfractal.contextfree.grammar.*;
import com.nextbreakpoint.nextfractal.contextfree.grammar.enums.CompilePhase;
import com.nextbreakpoint.nextfractal.contextfree.grammar.enums.ExpType;
import com.nextbreakpoint.nextfractal.contextfree.grammar.enums.FlagType;
import com.nextbreakpoint.nextfractal.contextfree.grammar.enums.RepElemType;
import org.antlr.v4.runtime.Token;

import java.util.List;

import static com.nextbreakpoint.nextfractal.contextfree.grammar.enums.FlagType.*;

public class ASTPathCommand extends ASTReplacement {
	private double miterLimit;
	private double strokeWidth;
	private ASTExpression parameters;
	private CommandInfo infoCache;
	private int flags;

	public ASTPathCommand(CFDGDriver driver, Token location) {
		super(driver, null, RepElemType.command, location);
		this.miterLimit = 4.0;
		this.strokeWidth = 0.1;
		this.parameters = null;
		this.infoCache = new CommandInfo();
		this.flags = FlagType.CF_MITER_JOIN.getMask() + FlagType.CF_BUTT_CAP.getMask() + CF_FILL.getMask();
	}

	public ASTPathCommand(CFDGDriver driver, String s, ASTModification mods, ASTExpression params, Token location) {
		super(driver, mods, RepElemType.command, location);
		this.miterLimit = 4.0;
		this.strokeWidth = 0.1;
		this.parameters = params;
		this.infoCache = new CommandInfo();
		this.flags = FlagType.CF_MITER_JOIN.getMask() + FlagType.CF_BUTT_CAP.getMask();
		//TODO controllare
		if (s.equals("FILL")) {
			this.flags |= CF_FILL.getMask();
		} else if (!s.equals("STROKE")) {
			Logger.error("Unknown path command/operation", location);
		}
	}

	public double getMiterLimit() {
		return miterLimit;
	}

	public double getStrokeWidth() {
		return strokeWidth;
	}

	public ASTExpression getParameters() {
		return parameters;
	}

	public int getFlags() {
		return flags;
	}

	@Override
	public void traverse(Shape parent, boolean tr, RTI rti) {
		if (rti.isOpsOnly()) {
			Logger.error("Path commands not allowed at this point", location);
		}

		Shape child = new Shape(parent);
		double width = strokeWidth;
		replace(child, rti);

		double[] value = new double[1];
		if (parameters != null && parameters.evaluate(value, 1, rti) != 1) {
			Logger.error("Error computing stroke width", location);
		}
		width = value[0];

		CommandInfo info = null;

		if (rti.getCurrentPath().isCached()) {
			CommandInfo next = rti.getCurrentCommand().next();
			if (next == rti.getCurrentPath().getCommandInfo().end()) {
				Logger.error("Not enough path commands in cache", location);
			}
			info = next;
		} else {
			if (rti.getCurrentPath().getPath().getTotalVertices() == 0) {
				Logger.error("Path commands must be preceeded by at least one path operation", location);
			}
			rti.setWantCommand(false);
			rti.getCurrentPath().finish(false, rti);

			//TODO controllare

			infoCache.tryInit(rti.getIndex(), rti.getCurrentPath(), width, this);
			if (infoCache.getPathUID() == rti.getCurrentPath().getPathUID() && infoCache.getIndex() == rti.getIndex()) {
				rti.getCurrentPath().getCommandInfo().pushBack(infoCache);
			} else {
				rti.getCurrentPath().getCommandInfo().emplaceBack(rti.getIndex(), rti.getCurrentPath(), width, this);
			}
			info = rti.getCurrentPath().getCommandInfo().back();
		}

		rti.processPathCommand(child, info);
	}

	@Override
	public void compile(CompilePhase ph) {
		super.compile(ph);
		if (parameters != null) {
			parameters = parameters.compile(ph);
		}

		switch (ph) {
			case TypeCheck: {
				getChildChange().addEntropy((flags & CF_FILL.getMask()) != 0 ? "FILL" : "STROKE");
				ASTExpression w = AST.getFlagsAndStroke(getChildChange().getModExp(), flags);
				if (w != null) {
					if (parameters != null) {
						Logger.error("Cannot have a stroke adjustment in a v3 path command", w.getLocation());
					} else if (w.size() != 1 || w.getType() != ExpType.NumericType || w.evaluate(null, 0) != 1) {
						Logger.error("Stroke adjustment is ill-formed", w.getLocation());
					}
					parameters = w;
				}

				if (parameters == null) {
					return;
				}

				ASTExpression stroke = null;
				ASTExpression flags = null;
				List<ASTExpression> pcmdParams = AST.extract(parameters);
				switch (pcmdParams.size()) {
					case 2: {
						stroke = pcmdParams.get(0);
						flags = pcmdParams.get(1);
						break;
					}
					case 1: {
						flags = pcmdParams.get(1);
						break;
					}
					default: {
						Logger.error("Bad expression type in path command parameters", location);
						return;
					}
				}

				if (stroke != null) {
					if ((this.flags & CF_FILL.getMask()) != 0) {
						Logger.warning("Stroke width only useful for STROKE commands", stroke.getLocation());
					}
					double[] value = new double[1];
					value[0] = strokeWidth;
					if (stroke.getType() != ExpType.NumericType || stroke.evaluate(null, 0) != 1) {
						Logger.error("Stroke width expression must be numeric scalar", stroke.getLocation());
					} else if (!stroke.isConstant() || stroke.evaluate(value, 1) != 1) {
						parameters = stroke;
					}
					strokeWidth = value[0];
				}

				if (flags != null) {
					if (flags.getType() != ExpType.FlagType) {
						Logger.error("Unexpected argument in path command", flags.getLocation());
						return;
					}
					if (flags != null) {
						flags = flags.simplify();
					}

					//TODO controllare

					if (flags instanceof ASTReal) {
						int f = (int)((ASTReal)flags).getValue();
						if ((f & CF_JOIN_PRESENT.getMask()) != 0) {
							this.flags &= ~CF_JOIN_MASK.getMask();
						}
						if ((f & CF_CAP_PRESENT.getMask()) != 0) {
							this.flags &= ~CF_CAP_MASK.getMask();
						}
						this.flags |= f;
						if ((this.flags & CF_FILL.getMask()) != 0 && (this.flags & (CF_JOIN_PRESENT.getMask() | CF_CAP_PRESENT.getMask())) != 0) {
							Logger.warning("Stroke flags only useful for STROKE commands", flags.getLocation());
						}
					} else {
						Logger.error("Flag expressions must be constant", flags.getLocation());
					}
				}

				break;
			}

			case Simplify: {
				if (parameters != null) {
					parameters = parameters.simplify();
				}
				break;
			}

			default:
				break;
		}
	}
}

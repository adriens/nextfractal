package com.nextbreakpoint.nextfractal.mandelbrot.interpreter;

import com.nextbreakpoint.nextfractal.mandelbrot.compiler.CompiledExpression;
import com.nextbreakpoint.nextfractal.mandelbrot.compiler.ExpressionContext;

import static com.nextbreakpoint.nextfractal.mandelbrot.core.Expression.*;

import com.nextbreakpoint.nextfractal.mandelbrot.core.Number;

public class InterpreterFuncFloor implements CompiledExpression {
	private CompiledExpression[] arguments;
	private int index;
	
	public InterpreterFuncFloor(ExpressionContext context, CompiledExpression[] arguments) {
		this.index = context.newNumberIndex();
		this.arguments = arguments;
	}

	@Override
	public double evaluateReal(InterpreterContext context) {
		return funcFloor(arguments[0].evaluateReal(context));
	}

	@Override
	public Number evaluate(InterpreterContext context) {
		return context.getNumber(index).set(funcFloor(arguments[0].evaluateReal(context)));
	}

	@Override
	public boolean isReal() {
		return true;
	}
}

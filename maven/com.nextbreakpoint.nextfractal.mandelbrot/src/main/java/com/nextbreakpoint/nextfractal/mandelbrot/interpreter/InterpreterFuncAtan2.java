package com.nextbreakpoint.nextfractal.mandelbrot.interpreter;

import com.nextbreakpoint.nextfractal.mandelbrot.compiler.CompiledExpression;
import com.nextbreakpoint.nextfractal.mandelbrot.compiler.ExpressionContext;

import static com.nextbreakpoint.nextfractal.mandelbrot.core.Expression.*;

import com.nextbreakpoint.nextfractal.mandelbrot.core.Number;

public class InterpreterFuncAtan2 implements CompiledExpression {
	private CompiledExpression[] arguments;
	private int index;
	
	public InterpreterFuncAtan2(ExpressionContext context, CompiledExpression[] arguments) {
		this.index = context.newNumberIndex();
		this.arguments = arguments;
	}

	@Override
	public double evaluateReal(InterpreterContext context) {
		return funcAtan2(arguments[0].evaluateReal(context), arguments[1].evaluateReal(context));
	}

	@Override
	public Number evaluate(InterpreterContext context) {
		return context.getNumber(index).set(funcAtan2(arguments[0].evaluateReal(context), arguments[1].evaluateReal(context)));
	}

	@Override
	public boolean isReal() {
		return true;
	}
}

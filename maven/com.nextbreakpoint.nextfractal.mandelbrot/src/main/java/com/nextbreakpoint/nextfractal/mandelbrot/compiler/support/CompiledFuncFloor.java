package com.nextbreakpoint.nextfractal.mandelbrot.compiler.support;

import static com.nextbreakpoint.nextfractal.mandelbrot.core.Expression.funcFloor;

import java.util.Map;

import com.nextbreakpoint.nextfractal.mandelbrot.compiler.CompilerVariable;
import com.nextbreakpoint.nextfractal.mandelbrot.compiler.ExpressionContext;
import com.nextbreakpoint.nextfractal.mandelbrot.compiler.InterpreterContext;
import com.nextbreakpoint.nextfractal.mandelbrot.core.Number;

public class CompiledFuncFloor implements CompiledExpression {
	private CompiledExpression[] arguments;
	private int index;
	
	public CompiledFuncFloor(ExpressionContext context, CompiledExpression[] arguments) {
		this.index = context.newNumberIndex();
		this.arguments = arguments;
	}

	@Override
	public double evaluateReal(InterpreterContext context, Map<String, CompilerVariable> scope) {
		return funcFloor(arguments[0].evaluateReal(context, scope));
	}

	@Override
	public Number evaluate(InterpreterContext context, Map<String, CompilerVariable> scope) {
		return context.getNumber(index).set(funcFloor(arguments[0].evaluateReal(context, scope)));
	}

	@Override
	public boolean isReal() {
		return true;
	}
}
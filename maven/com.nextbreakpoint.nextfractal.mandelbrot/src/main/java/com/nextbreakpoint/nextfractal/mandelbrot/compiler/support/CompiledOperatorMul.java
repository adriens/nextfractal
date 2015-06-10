package com.nextbreakpoint.nextfractal.mandelbrot.compiler.support;

import static com.nextbreakpoint.nextfractal.mandelbrot.core.Expression.opMul;

import java.util.Map;

import com.nextbreakpoint.nextfractal.mandelbrot.compiler.CompilerVariable;
import com.nextbreakpoint.nextfractal.mandelbrot.compiler.ExpressionContext;
import com.nextbreakpoint.nextfractal.mandelbrot.compiler.InterpreterContext;
import com.nextbreakpoint.nextfractal.mandelbrot.core.Number;

public class CompiledOperatorMul implements CompiledExpression {
	private CompiledExpression exp1;
	private CompiledExpression exp2;
	private int index;
	
	public CompiledOperatorMul(ExpressionContext context, CompiledExpression exp1, CompiledExpression exp2) {
		this.index = context.newNumberIndex();
		this.exp1 = exp1;
		this.exp2 = exp2;
	}

	@Override
	public double evaluateReal(InterpreterContext context, Map<String, CompilerVariable> scope) {
		return opMul(exp1.evaluateReal(context, scope), exp2.evaluateReal(context, scope));
	}

	@Override
	public Number evaluate(InterpreterContext context, Map<String, CompilerVariable> scope) {
		return context.getNumber(index).set(opMul(exp1.evaluateReal(context, scope), exp2.evaluateReal(context, scope)));
	}

	@Override
	public boolean isReal() {
		return true;
	}
}
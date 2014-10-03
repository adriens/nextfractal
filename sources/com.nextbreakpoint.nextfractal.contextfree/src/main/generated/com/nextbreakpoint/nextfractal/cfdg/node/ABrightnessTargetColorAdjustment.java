/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.nextbreakpoint.nextfractal.cfdg.node;

import com.nextbreakpoint.nextfractal.cfdg.analysis.Analysis;

@SuppressWarnings("nls")
public final class ABrightnessTargetColorAdjustment extends PTargetColorAdjustment
{
    private TTargetBrightnessToken _targetBrightnessToken_;
    private PExpression _expression_;

    public ABrightnessTargetColorAdjustment()
    {
        // Constructor
    }

    public ABrightnessTargetColorAdjustment(
        @SuppressWarnings("hiding") TTargetBrightnessToken _targetBrightnessToken_,
        @SuppressWarnings("hiding") PExpression _expression_)
    {
        // Constructor
        setTargetBrightnessToken(_targetBrightnessToken_);

        setExpression(_expression_);

    }

    @Override
    public Object clone()
    {
        return new ABrightnessTargetColorAdjustment(
            cloneNode(this._targetBrightnessToken_),
            cloneNode(this._expression_));
    }

    @Override
	public void apply(Switch sw)
    {
        ((Analysis) sw).caseABrightnessTargetColorAdjustment(this);
    }

    public TTargetBrightnessToken getTargetBrightnessToken()
    {
        return this._targetBrightnessToken_;
    }

    public void setTargetBrightnessToken(TTargetBrightnessToken node)
    {
        if(this._targetBrightnessToken_ != null)
        {
            this._targetBrightnessToken_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._targetBrightnessToken_ = node;
    }

    public PExpression getExpression()
    {
        return this._expression_;
    }

    public void setExpression(PExpression node)
    {
        if(this._expression_ != null)
        {
            this._expression_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._expression_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._targetBrightnessToken_)
            + toString(this._expression_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._targetBrightnessToken_ == child)
        {
            this._targetBrightnessToken_ = null;
            return;
        }

        if(this._expression_ == child)
        {
            this._expression_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._targetBrightnessToken_ == oldChild)
        {
            setTargetBrightnessToken((TTargetBrightnessToken) newChild);
            return;
        }

        if(this._expression_ == oldChild)
        {
            setExpression((PExpression) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}

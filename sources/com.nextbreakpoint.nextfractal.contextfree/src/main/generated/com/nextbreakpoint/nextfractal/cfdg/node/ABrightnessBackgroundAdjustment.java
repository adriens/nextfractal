/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.nextbreakpoint.nextfractal.cfdg.node;

import com.nextbreakpoint.nextfractal.cfdg.analysis.Analysis;

@SuppressWarnings("nls")
public final class ABrightnessBackgroundAdjustment extends PBackgroundAdjustment
{
    private TBrightnessToken _brightnessToken_;
    private PExpression _expression_;

    public ABrightnessBackgroundAdjustment()
    {
        // Constructor
    }

    public ABrightnessBackgroundAdjustment(
        @SuppressWarnings("hiding") TBrightnessToken _brightnessToken_,
        @SuppressWarnings("hiding") PExpression _expression_)
    {
        // Constructor
        setBrightnessToken(_brightnessToken_);

        setExpression(_expression_);

    }

    @Override
    public Object clone()
    {
        return new ABrightnessBackgroundAdjustment(
            cloneNode(this._brightnessToken_),
            cloneNode(this._expression_));
    }

    @Override
	public void apply(Switch sw)
    {
        ((Analysis) sw).caseABrightnessBackgroundAdjustment(this);
    }

    public TBrightnessToken getBrightnessToken()
    {
        return this._brightnessToken_;
    }

    public void setBrightnessToken(TBrightnessToken node)
    {
        if(this._brightnessToken_ != null)
        {
            this._brightnessToken_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._brightnessToken_ = node;
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
            + toString(this._brightnessToken_)
            + toString(this._expression_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._brightnessToken_ == child)
        {
            this._brightnessToken_ = null;
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
        if(this._brightnessToken_ == oldChild)
        {
            setBrightnessToken((TBrightnessToken) newChild);
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

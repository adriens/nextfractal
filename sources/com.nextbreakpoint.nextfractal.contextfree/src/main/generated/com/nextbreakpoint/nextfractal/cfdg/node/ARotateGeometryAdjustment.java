/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.nextbreakpoint.nextfractal.cfdg.node;

import com.nextbreakpoint.nextfractal.cfdg.analysis.Analysis;

@SuppressWarnings("nls")
public final class ARotateGeometryAdjustment extends PGeometryAdjustment
{
    private TRotateToken _rotateToken_;
    private PExpression _expression_;

    public ARotateGeometryAdjustment()
    {
        // Constructor
    }

    public ARotateGeometryAdjustment(
        @SuppressWarnings("hiding") TRotateToken _rotateToken_,
        @SuppressWarnings("hiding") PExpression _expression_)
    {
        // Constructor
        setRotateToken(_rotateToken_);

        setExpression(_expression_);

    }

    @Override
    public Object clone()
    {
        return new ARotateGeometryAdjustment(
            cloneNode(this._rotateToken_),
            cloneNode(this._expression_));
    }

    @Override
	public void apply(Switch sw)
    {
        ((Analysis) sw).caseARotateGeometryAdjustment(this);
    }

    public TRotateToken getRotateToken()
    {
        return this._rotateToken_;
    }

    public void setRotateToken(TRotateToken node)
    {
        if(this._rotateToken_ != null)
        {
            this._rotateToken_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._rotateToken_ = node;
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
            + toString(this._rotateToken_)
            + toString(this._expression_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._rotateToken_ == child)
        {
            this._rotateToken_ = null;
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
        if(this._rotateToken_ == oldChild)
        {
            setRotateToken((TRotateToken) newChild);
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

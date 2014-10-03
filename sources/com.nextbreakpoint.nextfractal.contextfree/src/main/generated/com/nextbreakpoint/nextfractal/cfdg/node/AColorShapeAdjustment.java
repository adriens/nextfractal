/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.nextbreakpoint.nextfractal.cfdg.node;

import com.nextbreakpoint.nextfractal.cfdg.analysis.Analysis;

@SuppressWarnings("nls")
public final class AColorShapeAdjustment extends PShapeAdjustment
{
    private PColorAdjustment _colorAdjustment_;

    public AColorShapeAdjustment()
    {
        // Constructor
    }

    public AColorShapeAdjustment(
        @SuppressWarnings("hiding") PColorAdjustment _colorAdjustment_)
    {
        // Constructor
        setColorAdjustment(_colorAdjustment_);

    }

    @Override
    public Object clone()
    {
        return new AColorShapeAdjustment(
            cloneNode(this._colorAdjustment_));
    }

    @Override
	public void apply(Switch sw)
    {
        ((Analysis) sw).caseAColorShapeAdjustment(this);
    }

    public PColorAdjustment getColorAdjustment()
    {
        return this._colorAdjustment_;
    }

    public void setColorAdjustment(PColorAdjustment node)
    {
        if(this._colorAdjustment_ != null)
        {
            this._colorAdjustment_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._colorAdjustment_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._colorAdjustment_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._colorAdjustment_ == child)
        {
            this._colorAdjustment_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._colorAdjustment_ == oldChild)
        {
            setColorAdjustment((PColorAdjustment) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}

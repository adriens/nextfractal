/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.nextbreakpoint.nextfractal.cfdg.node;

import com.nextbreakpoint.nextfractal.cfdg.analysis.Analysis;

@SuppressWarnings("nls")
public final class AStartshapeCfdgDeclaration extends PCfdgDeclaration
{
    private PStartshapeDeclaration _startshapeDeclaration_;

    public AStartshapeCfdgDeclaration()
    {
        // Constructor
    }

    public AStartshapeCfdgDeclaration(
        @SuppressWarnings("hiding") PStartshapeDeclaration _startshapeDeclaration_)
    {
        // Constructor
        setStartshapeDeclaration(_startshapeDeclaration_);

    }

    @Override
    public Object clone()
    {
        return new AStartshapeCfdgDeclaration(
            cloneNode(this._startshapeDeclaration_));
    }

    @Override
	public void apply(Switch sw)
    {
        ((Analysis) sw).caseAStartshapeCfdgDeclaration(this);
    }

    public PStartshapeDeclaration getStartshapeDeclaration()
    {
        return this._startshapeDeclaration_;
    }

    public void setStartshapeDeclaration(PStartshapeDeclaration node)
    {
        if(this._startshapeDeclaration_ != null)
        {
            this._startshapeDeclaration_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._startshapeDeclaration_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._startshapeDeclaration_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._startshapeDeclaration_ == child)
        {
            this._startshapeDeclaration_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._startshapeDeclaration_ == oldChild)
        {
            setStartshapeDeclaration((PStartshapeDeclaration) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}

/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.nextbreakpoint.nextfractal.cfdg.node;

import com.nextbreakpoint.nextfractal.cfdg.analysis.Analysis;

@SuppressWarnings("nls")
public final class APathCfdgDeclaration extends PCfdgDeclaration
{
    private PPathDeclaration _pathDeclaration_;

    public APathCfdgDeclaration()
    {
        // Constructor
    }

    public APathCfdgDeclaration(
        @SuppressWarnings("hiding") PPathDeclaration _pathDeclaration_)
    {
        // Constructor
        setPathDeclaration(_pathDeclaration_);

    }

    @Override
    public Object clone()
    {
        return new APathCfdgDeclaration(
            cloneNode(this._pathDeclaration_));
    }

    @Override
	public void apply(Switch sw)
    {
        ((Analysis) sw).caseAPathCfdgDeclaration(this);
    }

    public PPathDeclaration getPathDeclaration()
    {
        return this._pathDeclaration_;
    }

    public void setPathDeclaration(PPathDeclaration node)
    {
        if(this._pathDeclaration_ != null)
        {
            this._pathDeclaration_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._pathDeclaration_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._pathDeclaration_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._pathDeclaration_ == child)
        {
            this._pathDeclaration_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._pathDeclaration_ == oldChild)
        {
            setPathDeclaration((PPathDeclaration) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}

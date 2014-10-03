/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.nextbreakpoint.nextfractal.cfdg.node;

import com.nextbreakpoint.nextfractal.cfdg.analysis.Analysis;

@SuppressWarnings("nls")
public final class ASizeCfdgDeclaration extends PCfdgDeclaration
{
    private PSizeDeclaration _sizeDeclaration_;

    public ASizeCfdgDeclaration()
    {
        // Constructor
    }

    public ASizeCfdgDeclaration(
        @SuppressWarnings("hiding") PSizeDeclaration _sizeDeclaration_)
    {
        // Constructor
        setSizeDeclaration(_sizeDeclaration_);

    }

    @Override
    public Object clone()
    {
        return new ASizeCfdgDeclaration(
            cloneNode(this._sizeDeclaration_));
    }

    @Override
	public void apply(Switch sw)
    {
        ((Analysis) sw).caseASizeCfdgDeclaration(this);
    }

    public PSizeDeclaration getSizeDeclaration()
    {
        return this._sizeDeclaration_;
    }

    public void setSizeDeclaration(PSizeDeclaration node)
    {
        if(this._sizeDeclaration_ != null)
        {
            this._sizeDeclaration_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._sizeDeclaration_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._sizeDeclaration_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._sizeDeclaration_ == child)
        {
            this._sizeDeclaration_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._sizeDeclaration_ == oldChild)
        {
            setSizeDeclaration((PSizeDeclaration) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}

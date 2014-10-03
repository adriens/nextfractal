/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.nextbreakpoint.nextfractal.cfdg.node;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import com.nextbreakpoint.nextfractal.cfdg.analysis.Analysis;

@SuppressWarnings("nls")
public final class AUnorderedShapeReplacementDeclaration extends PShapeReplacementDeclaration
{
    private TNumber _number_;
    private TStar _star_;
    private TLSbkt _lSbkt_;
    private final LinkedList<PShapeAdjustment> _shapeAdjustment_ = new LinkedList<PShapeAdjustment>();
    private TRSbkt _rSbkt_;
    private PShapeReplacementBlock _shapeReplacementBlock_;

    public AUnorderedShapeReplacementDeclaration()
    {
        // Constructor
    }

    public AUnorderedShapeReplacementDeclaration(
        @SuppressWarnings("hiding") TNumber _number_,
        @SuppressWarnings("hiding") TStar _star_,
        @SuppressWarnings("hiding") TLSbkt _lSbkt_,
        @SuppressWarnings("hiding") List<PShapeAdjustment> _shapeAdjustment_,
        @SuppressWarnings("hiding") TRSbkt _rSbkt_,
        @SuppressWarnings("hiding") PShapeReplacementBlock _shapeReplacementBlock_)
    {
        // Constructor
        setNumber(_number_);

        setStar(_star_);

        setLSbkt(_lSbkt_);

        setShapeAdjustment(_shapeAdjustment_);

        setRSbkt(_rSbkt_);

        setShapeReplacementBlock(_shapeReplacementBlock_);

    }

    @Override
    public Object clone()
    {
        return new AUnorderedShapeReplacementDeclaration(
            cloneNode(this._number_),
            cloneNode(this._star_),
            cloneNode(this._lSbkt_),
            cloneList(this._shapeAdjustment_),
            cloneNode(this._rSbkt_),
            cloneNode(this._shapeReplacementBlock_));
    }

    @Override
	public void apply(Switch sw)
    {
        ((Analysis) sw).caseAUnorderedShapeReplacementDeclaration(this);
    }

    public TNumber getNumber()
    {
        return this._number_;
    }

    public void setNumber(TNumber node)
    {
        if(this._number_ != null)
        {
            this._number_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._number_ = node;
    }

    public TStar getStar()
    {
        return this._star_;
    }

    public void setStar(TStar node)
    {
        if(this._star_ != null)
        {
            this._star_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._star_ = node;
    }

    public TLSbkt getLSbkt()
    {
        return this._lSbkt_;
    }

    public void setLSbkt(TLSbkt node)
    {
        if(this._lSbkt_ != null)
        {
            this._lSbkt_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._lSbkt_ = node;
    }

    public LinkedList<PShapeAdjustment> getShapeAdjustment()
    {
        return this._shapeAdjustment_;
    }

    public void setShapeAdjustment(List<PShapeAdjustment> list)
    {
        this._shapeAdjustment_.clear();
        this._shapeAdjustment_.addAll(list);
        for(PShapeAdjustment e : list)
        {
            if(e.parent() != null)
            {
                e.parent().removeChild(e);
            }

            e.parent(this);
        }
    }

    public TRSbkt getRSbkt()
    {
        return this._rSbkt_;
    }

    public void setRSbkt(TRSbkt node)
    {
        if(this._rSbkt_ != null)
        {
            this._rSbkt_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._rSbkt_ = node;
    }

    public PShapeReplacementBlock getShapeReplacementBlock()
    {
        return this._shapeReplacementBlock_;
    }

    public void setShapeReplacementBlock(PShapeReplacementBlock node)
    {
        if(this._shapeReplacementBlock_ != null)
        {
            this._shapeReplacementBlock_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._shapeReplacementBlock_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._number_)
            + toString(this._star_)
            + toString(this._lSbkt_)
            + toString(this._shapeAdjustment_)
            + toString(this._rSbkt_)
            + toString(this._shapeReplacementBlock_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._number_ == child)
        {
            this._number_ = null;
            return;
        }

        if(this._star_ == child)
        {
            this._star_ = null;
            return;
        }

        if(this._lSbkt_ == child)
        {
            this._lSbkt_ = null;
            return;
        }

        if(this._shapeAdjustment_.remove(child))
        {
            return;
        }

        if(this._rSbkt_ == child)
        {
            this._rSbkt_ = null;
            return;
        }

        if(this._shapeReplacementBlock_ == child)
        {
            this._shapeReplacementBlock_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._number_ == oldChild)
        {
            setNumber((TNumber) newChild);
            return;
        }

        if(this._star_ == oldChild)
        {
            setStar((TStar) newChild);
            return;
        }

        if(this._lSbkt_ == oldChild)
        {
            setLSbkt((TLSbkt) newChild);
            return;
        }

        for(ListIterator<PShapeAdjustment> i = this._shapeAdjustment_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set((PShapeAdjustment) newChild);
                    newChild.parent(this);
                    oldChild.parent(null);
                    return;
                }

                i.remove();
                oldChild.parent(null);
                return;
            }
        }

        if(this._rSbkt_ == oldChild)
        {
            setRSbkt((TRSbkt) newChild);
            return;
        }

        if(this._shapeReplacementBlock_ == oldChild)
        {
            setShapeReplacementBlock((PShapeReplacementBlock) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}

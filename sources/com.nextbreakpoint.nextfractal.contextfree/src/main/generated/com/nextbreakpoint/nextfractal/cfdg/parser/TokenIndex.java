/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.nextbreakpoint.nextfractal.cfdg.parser;

import com.nextbreakpoint.nextfractal.cfdg.analysis.AnalysisAdapter;
import com.nextbreakpoint.nextfractal.cfdg.node.EOF;
import com.nextbreakpoint.nextfractal.cfdg.node.TAlphaToken;
import com.nextbreakpoint.nextfractal.cfdg.node.TArrow;
import com.nextbreakpoint.nextfractal.cfdg.node.TBackground;
import com.nextbreakpoint.nextfractal.cfdg.node.TBar;
import com.nextbreakpoint.nextfractal.cfdg.node.TBrightnessToken;
import com.nextbreakpoint.nextfractal.cfdg.node.TComma;
import com.nextbreakpoint.nextfractal.cfdg.node.TCommand;
import com.nextbreakpoint.nextfractal.cfdg.node.TFilename;
import com.nextbreakpoint.nextfractal.cfdg.node.TFlipToken;
import com.nextbreakpoint.nextfractal.cfdg.node.TFunctionArg0;
import com.nextbreakpoint.nextfractal.cfdg.node.TFunctionArg1;
import com.nextbreakpoint.nextfractal.cfdg.node.TFunctionArg2;
import com.nextbreakpoint.nextfractal.cfdg.node.THueToken;
import com.nextbreakpoint.nextfractal.cfdg.node.TInclude;
import com.nextbreakpoint.nextfractal.cfdg.node.TLCbkt;
import com.nextbreakpoint.nextfractal.cfdg.node.TLRbkt;
import com.nextbreakpoint.nextfractal.cfdg.node.TLSbkt;
import com.nextbreakpoint.nextfractal.cfdg.node.TMinus;
import com.nextbreakpoint.nextfractal.cfdg.node.TNumber;
import com.nextbreakpoint.nextfractal.cfdg.node.TOperation;
import com.nextbreakpoint.nextfractal.cfdg.node.TParametersToken;
import com.nextbreakpoint.nextfractal.cfdg.node.TPath;
import com.nextbreakpoint.nextfractal.cfdg.node.TPlus;
import com.nextbreakpoint.nextfractal.cfdg.node.TRCbkt;
import com.nextbreakpoint.nextfractal.cfdg.node.TRRbkt;
import com.nextbreakpoint.nextfractal.cfdg.node.TRSbkt;
import com.nextbreakpoint.nextfractal.cfdg.node.TRotateToken;
import com.nextbreakpoint.nextfractal.cfdg.node.TRule;
import com.nextbreakpoint.nextfractal.cfdg.node.TRxToken;
import com.nextbreakpoint.nextfractal.cfdg.node.TRyToken;
import com.nextbreakpoint.nextfractal.cfdg.node.TSaturationToken;
import com.nextbreakpoint.nextfractal.cfdg.node.TSize;
import com.nextbreakpoint.nextfractal.cfdg.node.TSizeToken;
import com.nextbreakpoint.nextfractal.cfdg.node.TSkewToken;
import com.nextbreakpoint.nextfractal.cfdg.node.TSlash;
import com.nextbreakpoint.nextfractal.cfdg.node.TStar;
import com.nextbreakpoint.nextfractal.cfdg.node.TStartshape;
import com.nextbreakpoint.nextfractal.cfdg.node.TString;
import com.nextbreakpoint.nextfractal.cfdg.node.TStrokewidthToken;
import com.nextbreakpoint.nextfractal.cfdg.node.TTargetAlphaToken;
import com.nextbreakpoint.nextfractal.cfdg.node.TTargetBrightnessToken;
import com.nextbreakpoint.nextfractal.cfdg.node.TTargetHueToken;
import com.nextbreakpoint.nextfractal.cfdg.node.TTargetSaturationToken;
import com.nextbreakpoint.nextfractal.cfdg.node.TTile;
import com.nextbreakpoint.nextfractal.cfdg.node.TX1Token;
import com.nextbreakpoint.nextfractal.cfdg.node.TX2Token;
import com.nextbreakpoint.nextfractal.cfdg.node.TXToken;
import com.nextbreakpoint.nextfractal.cfdg.node.TY1Token;
import com.nextbreakpoint.nextfractal.cfdg.node.TY2Token;
import com.nextbreakpoint.nextfractal.cfdg.node.TYToken;
import com.nextbreakpoint.nextfractal.cfdg.node.TZToken;

class TokenIndex extends AnalysisAdapter
{
    int index;

    @Override
    public void caseTBar(@SuppressWarnings("unused") TBar node)
    {
        this.index = 0;
    }

    @Override
    public void caseTStar(@SuppressWarnings("unused") TStar node)
    {
        this.index = 1;
    }

    @Override
    public void caseTPlus(@SuppressWarnings("unused") TPlus node)
    {
        this.index = 2;
    }

    @Override
    public void caseTMinus(@SuppressWarnings("unused") TMinus node)
    {
        this.index = 3;
    }

    @Override
    public void caseTSlash(@SuppressWarnings("unused") TSlash node)
    {
        this.index = 4;
    }

    @Override
    public void caseTArrow(@SuppressWarnings("unused") TArrow node)
    {
        this.index = 5;
    }

    @Override
    public void caseTComma(@SuppressWarnings("unused") TComma node)
    {
        this.index = 6;
    }

    @Override
    public void caseTLCbkt(@SuppressWarnings("unused") TLCbkt node)
    {
        this.index = 7;
    }

    @Override
    public void caseTRCbkt(@SuppressWarnings("unused") TRCbkt node)
    {
        this.index = 8;
    }

    @Override
    public void caseTLRbkt(@SuppressWarnings("unused") TLRbkt node)
    {
        this.index = 9;
    }

    @Override
    public void caseTRRbkt(@SuppressWarnings("unused") TRRbkt node)
    {
        this.index = 10;
    }

    @Override
    public void caseTLSbkt(@SuppressWarnings("unused") TLSbkt node)
    {
        this.index = 11;
    }

    @Override
    public void caseTRSbkt(@SuppressWarnings("unused") TRSbkt node)
    {
        this.index = 12;
    }

    @Override
    public void caseTHueToken(@SuppressWarnings("unused") THueToken node)
    {
        this.index = 13;
    }

    @Override
    public void caseTSaturationToken(@SuppressWarnings("unused") TSaturationToken node)
    {
        this.index = 14;
    }

    @Override
    public void caseTBrightnessToken(@SuppressWarnings("unused") TBrightnessToken node)
    {
        this.index = 15;
    }

    @Override
    public void caseTAlphaToken(@SuppressWarnings("unused") TAlphaToken node)
    {
        this.index = 16;
    }

    @Override
    public void caseTTargetHueToken(@SuppressWarnings("unused") TTargetHueToken node)
    {
        this.index = 17;
    }

    @Override
    public void caseTTargetSaturationToken(@SuppressWarnings("unused") TTargetSaturationToken node)
    {
        this.index = 18;
    }

    @Override
    public void caseTTargetBrightnessToken(@SuppressWarnings("unused") TTargetBrightnessToken node)
    {
        this.index = 19;
    }

    @Override
    public void caseTTargetAlphaToken(@SuppressWarnings("unused") TTargetAlphaToken node)
    {
        this.index = 20;
    }

    @Override
    public void caseTXToken(@SuppressWarnings("unused") TXToken node)
    {
        this.index = 21;
    }

    @Override
    public void caseTYToken(@SuppressWarnings("unused") TYToken node)
    {
        this.index = 22;
    }

    @Override
    public void caseTZToken(@SuppressWarnings("unused") TZToken node)
    {
        this.index = 23;
    }

    @Override
    public void caseTRotateToken(@SuppressWarnings("unused") TRotateToken node)
    {
        this.index = 24;
    }

    @Override
    public void caseTSizeToken(@SuppressWarnings("unused") TSizeToken node)
    {
        this.index = 25;
    }

    @Override
    public void caseTFlipToken(@SuppressWarnings("unused") TFlipToken node)
    {
        this.index = 26;
    }

    @Override
    public void caseTSkewToken(@SuppressWarnings("unused") TSkewToken node)
    {
        this.index = 27;
    }

    @Override
    public void caseTParametersToken(@SuppressWarnings("unused") TParametersToken node)
    {
        this.index = 28;
    }

    @Override
    public void caseTStrokewidthToken(@SuppressWarnings("unused") TStrokewidthToken node)
    {
        this.index = 29;
    }

    @Override
    public void caseTX1Token(@SuppressWarnings("unused") TX1Token node)
    {
        this.index = 30;
    }

    @Override
    public void caseTY1Token(@SuppressWarnings("unused") TY1Token node)
    {
        this.index = 31;
    }

    @Override
    public void caseTX2Token(@SuppressWarnings("unused") TX2Token node)
    {
        this.index = 32;
    }

    @Override
    public void caseTY2Token(@SuppressWarnings("unused") TY2Token node)
    {
        this.index = 33;
    }

    @Override
    public void caseTRxToken(@SuppressWarnings("unused") TRxToken node)
    {
        this.index = 34;
    }

    @Override
    public void caseTRyToken(@SuppressWarnings("unused") TRyToken node)
    {
        this.index = 35;
    }

    @Override
    public void caseTCommand(@SuppressWarnings("unused") TCommand node)
    {
        this.index = 36;
    }

    @Override
    public void caseTOperation(@SuppressWarnings("unused") TOperation node)
    {
        this.index = 37;
    }

    @Override
    public void caseTFunctionArg0(@SuppressWarnings("unused") TFunctionArg0 node)
    {
        this.index = 38;
    }

    @Override
    public void caseTFunctionArg1(@SuppressWarnings("unused") TFunctionArg1 node)
    {
        this.index = 39;
    }

    @Override
    public void caseTFunctionArg2(@SuppressWarnings("unused") TFunctionArg2 node)
    {
        this.index = 40;
    }

    @Override
    public void caseTStartshape(@SuppressWarnings("unused") TStartshape node)
    {
        this.index = 41;
    }

    @Override
    public void caseTBackground(@SuppressWarnings("unused") TBackground node)
    {
        this.index = 42;
    }

    @Override
    public void caseTInclude(@SuppressWarnings("unused") TInclude node)
    {
        this.index = 43;
    }

    @Override
    public void caseTTile(@SuppressWarnings("unused") TTile node)
    {
        this.index = 44;
    }

    @Override
    public void caseTSize(@SuppressWarnings("unused") TSize node)
    {
        this.index = 45;
    }

    @Override
    public void caseTRule(@SuppressWarnings("unused") TRule node)
    {
        this.index = 46;
    }

    @Override
    public void caseTPath(@SuppressWarnings("unused") TPath node)
    {
        this.index = 47;
    }

    @Override
    public void caseTFilename(@SuppressWarnings("unused") TFilename node)
    {
        this.index = 48;
    }

    @Override
    public void caseTString(@SuppressWarnings("unused") TString node)
    {
        this.index = 49;
    }

    @Override
    public void caseTNumber(@SuppressWarnings("unused") TNumber node)
    {
        this.index = 50;
    }

    @Override
    public void caseEOF(@SuppressWarnings("unused") EOF node)
    {
        this.index = 51;
    }
}

package com.airwallex.rpncalc.operator;

import com.airwallex.rpncalc.ctx.RunningContext;

public abstract class AbstractOperator implements IRpnCalcOperator {
    protected int position;

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public void undo(RunningContext ctx) {

    }
}

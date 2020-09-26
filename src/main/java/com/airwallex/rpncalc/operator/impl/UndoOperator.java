package com.airwallex.rpncalc.operator.impl;

import com.airwallex.rpncalc.ctx.HistoryNode;
import com.airwallex.rpncalc.ctx.RunningContext;
import com.airwallex.rpncalc.error.ExecutionException;
import com.airwallex.rpncalc.operator.AbstractOperator;

public class UndoOperator extends AbstractOperator {
    @Override
    public void execute(RunningContext ctx) throws ExecutionException {
        if (ctx.isHistoryStackEmpty()) {
            ctx.popNumber();
        } else {
            HistoryNode historyNode = ctx.peekHistoryNode();
            historyNode.getOperator().undo(ctx);
        }
    }

    @Override
    public String getName() {
        return "undo";
    }
}

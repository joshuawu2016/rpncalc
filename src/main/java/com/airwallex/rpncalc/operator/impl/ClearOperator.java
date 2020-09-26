package com.airwallex.rpncalc.operator.impl;

import com.airwallex.rpncalc.ctx.HistoryNode;
import com.airwallex.rpncalc.ctx.RunningContext;
import com.airwallex.rpncalc.error.ExecutionException;
import com.airwallex.rpncalc.operator.AbstractOperator;

import java.math.BigDecimal;
import java.util.Deque;
import java.util.LinkedList;

public class ClearOperator extends AbstractOperator {
    @Override
    public void execute(RunningContext ctx) throws ExecutionException {
        BigDecimal bd1 = null;
        Deque<BigDecimal> ret = new LinkedList<>();
        while ((bd1 = ctx.popNumber()) != null) {
            ret.addLast(bd1);
        }
        ctx.pushHistoryNode(this, ret);
    }

    @Override
    public void undo(RunningContext ctx) {
        HistoryNode historyNode = ctx.popHistoryNode();
        Deque<BigDecimal> numbers = historyNode.getNumbers();
        while (!numbers.isEmpty()) {
            ctx.pushNumber(numbers.removeLast());
        }
    }

    @Override
    public String getName() {
        return "clear";
    }
}

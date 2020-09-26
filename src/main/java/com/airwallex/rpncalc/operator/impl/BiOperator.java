package com.airwallex.rpncalc.operator.impl;

import com.airwallex.rpncalc.ctx.HistoryNode;
import com.airwallex.rpncalc.ctx.RunningContext;
import com.airwallex.rpncalc.error.ErrorCode;
import com.airwallex.rpncalc.error.ExecutionException;
import com.airwallex.rpncalc.operator.AbstractOperator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Deque;

public abstract class BiOperator extends AbstractOperator {
    /**
     * +,-,*,/
     * get two numbers from current number stack
     * calculate, push back result
     * construct history node(two numbers + operator), push to history node stack
     *
     * @param ctx
     * @return
     */
    @Override
    public void execute(RunningContext ctx) throws ExecutionException {
        if (ctx.numStackSize() < 2) {
            throw new ExecutionException(ErrorCode.InsufficientParams);
        }
        BigDecimal right = ctx.popNumber();
        BigDecimal left = ctx.popNumber();
        BigDecimal bd3 = null;
        try {
            bd3 = binOp(left, right);
        } catch (ExecutionException e) {
            if (left != null) ctx.pushNumber(left);
            if (right != null) ctx.pushNumber(right);
            throw e;
        }

        bd3 = bd3.setScale(16, RoundingMode.FLOOR);
        ctx.pushNumber(bd3);

        ctx.pushHistoryNode(this, right, left);

    }

    @Override
    public void undo(RunningContext ctx) {
        HistoryNode historyNode = ctx.popHistoryNode();
        ctx.popNumber();
        Deque<BigDecimal> numbers = historyNode.getNumbers();
        while (!numbers.isEmpty()) {
            ctx.pushNumber(numbers.removeLast());
        }
    }

    abstract BigDecimal binOp(BigDecimal bd1, BigDecimal bd2) throws ExecutionException;

}

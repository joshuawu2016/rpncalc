package com.airwallex.rpncalc.operator.impl;

import com.airwallex.rpncalc.ctx.HistoryNode;
import com.airwallex.rpncalc.ctx.RunningContext;
import com.airwallex.rpncalc.error.ErrorCode;
import com.airwallex.rpncalc.error.ExecutionException;
import com.airwallex.rpncalc.operator.AbstractOperator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Deque;

public class SqrtOperator extends AbstractOperator {
    static final MathContext mc = new MathContext(16);

    @Override
    public void execute(RunningContext ctx) throws ExecutionException {
        if (ctx.numStackSize() < 1) {
            throw new ExecutionException(ErrorCode.InsufficientParams);
        }
        BigDecimal bd1 = ctx.peekNumberStack();

        if (bd1.doubleValue() < 0.0) {
            throw new ExecutionException(ErrorCode.NegativeSqrt);
        }
        bd1 = ctx.popNumber();
        BigDecimal bd3 = bd1.sqrt(mc);
        bd3 = bd3.setScale(16, RoundingMode.FLOOR);
        ctx.pushNumber(bd3);

        ctx.pushHistoryNode(this, bd1);
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

    @Override
    public String getName() {
        return "sqrt";
    }
}

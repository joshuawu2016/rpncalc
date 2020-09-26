package com.airwallex.rpncalc.operator.impl;

import com.airwallex.rpncalc.ctx.RunningContext;
import com.airwallex.rpncalc.error.ErrorCode;
import com.airwallex.rpncalc.error.ExecutionException;
import com.airwallex.rpncalc.operator.AbstractOperator;

import java.math.BigDecimal;

public class NumberOperator extends AbstractOperator {
    String token = null;

    public NumberOperator(String v) {
        token = v;
    }

    @Override
    public void execute(RunningContext ctx) throws ExecutionException {
        BigDecimal num = null;
        try {
            num = new BigDecimal(token);
        } catch (NumberFormatException nfe) {
            throw new ExecutionException(ErrorCode.NumberNotValid);
        }

        if (num != null) {
            ctx.pushNumber(num);
        }
    }

    @Override
    public String getName() {
        return token;
    }

    @Override
    public String getType() {
        return "number";
    }
}

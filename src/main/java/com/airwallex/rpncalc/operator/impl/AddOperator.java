package com.airwallex.rpncalc.operator.impl;

import com.airwallex.rpncalc.error.ErrorCode;
import com.airwallex.rpncalc.error.ExecutionException;

import java.math.BigDecimal;

public class AddOperator extends BiOperator {
    @Override
    public String getName() {
        return "+";
    }

    @Override
    BigDecimal binOp(BigDecimal bd1, BigDecimal bd2) throws ExecutionException {
        if (bd1 == null || bd2 == null)
            throw new ExecutionException(ErrorCode.InsufficientParams);
        return bd1.add(bd2);
    }
}

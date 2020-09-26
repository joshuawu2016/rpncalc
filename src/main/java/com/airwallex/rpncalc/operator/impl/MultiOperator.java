package com.airwallex.rpncalc.operator.impl;

import com.airwallex.rpncalc.error.ErrorCode;
import com.airwallex.rpncalc.error.ExecutionException;

import java.math.BigDecimal;

public class MultiOperator extends BiOperator {
    @Override
    public String getName() {
        return "*";
    }

    @Override
    BigDecimal binOp(BigDecimal bd2, BigDecimal bd1) throws ExecutionException {
        if (bd1 == null || bd2 == null)
            throw new ExecutionException(ErrorCode.InsufficientParams);
        return bd2.multiply(bd1);
    }
}

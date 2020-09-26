package com.airwallex.rpncalc.operator.impl;

import com.airwallex.rpncalc.error.ErrorCode;
import com.airwallex.rpncalc.error.ExecutionException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DivideOperator extends BiOperator {
    @Override
    public String getName() {
        return "/";
    }

    @Override
    BigDecimal binOp(BigDecimal bd2, BigDecimal bd1) throws ExecutionException {
        if (bd1 == null || bd2 == null)
            throw new ExecutionException(ErrorCode.InsufficientParams);
        if (bd1.doubleValue() == 0.0)
            throw new ExecutionException(ErrorCode.ZeroDivide);
        return bd2.divide(bd1, 16, RoundingMode.FLOOR);
    }
}

package com.airwallex.rpncalc.operator.impl;

import com.airwallex.rpncalc.error.ErrorCode;
import com.airwallex.rpncalc.error.ExecutionException;

import java.math.BigDecimal;

public class SubtractOperator extends BiOperator {
    @Override
    public String getName() {
        return "-";
    }

    @Override
    BigDecimal binOp(BigDecimal left, BigDecimal right) throws ExecutionException {
        if (right == null || left == null)
            throw new ExecutionException(ErrorCode.InsufficientParams);
        return left.subtract(right);
    }
}

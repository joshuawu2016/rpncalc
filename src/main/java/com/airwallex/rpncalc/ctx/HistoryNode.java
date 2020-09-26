package com.airwallex.rpncalc.ctx;

import com.airwallex.rpncalc.operator.IRpnCalcOperator;

import java.math.BigDecimal;
import java.util.Deque;
import java.util.LinkedList;

public class HistoryNode {
    Deque<BigDecimal> numbers = new LinkedList<>();
    IRpnCalcOperator operator;

    public Deque<BigDecimal> getNumbers() {
        return numbers;
    }

    public void setNumbers(Deque<BigDecimal> numbers) {
        this.numbers = numbers;
    }

    public IRpnCalcOperator getOperator() {
        return operator;
    }

    public void setOperator(IRpnCalcOperator operator) {
        this.operator = operator;
    }
}

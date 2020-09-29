package com.airwallex.rpncalc.test.operator;

import com.airwallex.rpncalc.error.ErrorCode;
import com.airwallex.rpncalc.error.ExecutionException;
import com.airwallex.rpncalc.operator.impl.SqrtOperator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SqrtOperatorTest extends OperatorTestBase {
    public SqrtOperatorTest() {
        this.op = new SqrtOperator();
    }

    @Test
    public void execute_ok() throws ExecutionException {
        presetCurrNumberStack("2.1 3.2");
        this.op.execute(this.rc);
        assertCurrNumberStack("2.1 1.788854381999832");
    }

    @Test
    public void execute_exception1() throws ExecutionException {
        exceptionRule.expect(CustomMatcher.hasCode(ErrorCode.InsufficientParams));
        presetCurrNumberStack("");
        this.op.execute(this.rc);
    }

    @Test
    public void execute_exception_negative_sqrt() throws ExecutionException {
        exceptionRule.expect(CustomMatcher.hasCode(ErrorCode.NegativeSqrt));
        presetCurrNumberStack("-1");
        this.op.execute(this.rc);
    }

    @Test
    public void undo() throws ExecutionException {
        presetCurrNumberStack("2.9 2.1 3.2");
        this.op.execute(this.rc);
        this.op.undo(this.rc);
        assertCurrNumberStack("2.9 2.1 3.2");
    }

    @Test
    public void getName2() {
        assertEquals("sqrt", op.getName());
    }

}
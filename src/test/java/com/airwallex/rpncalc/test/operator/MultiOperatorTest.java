package com.airwallex.rpncalc.test.operator;

import com.airwallex.rpncalc.error.ErrorCode;
import com.airwallex.rpncalc.error.ExecutionException;
import com.airwallex.rpncalc.operator.impl.MultiOperator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MultiOperatorTest extends OperatorTestBase {
    public MultiOperatorTest() {
        this.op = new MultiOperator();
    }

    @Test
    public void execute_ok() throws ExecutionException {
        presetCurrNumberStack("2.1 3.2");
        this.op.execute(this.rc);
        assertCurrNumberStack("6.72");
    }

    @Test
    public void execute_exception1() throws ExecutionException {
        exceptionRule.expect(CustomMatcher.hasCode(ErrorCode.InsufficientParams));
        presetCurrNumberStack("");
        this.op.execute(this.rc);
    }

    @Test
    public void execute_exception2() throws ExecutionException {
        exceptionRule.expect(CustomMatcher.hasCode(ErrorCode.InsufficientParams));
        presetCurrNumberStack("3.2");
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
        assertEquals("*", op.getName());
    }

}
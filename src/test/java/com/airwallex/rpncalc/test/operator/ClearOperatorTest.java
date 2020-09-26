package com.airwallex.rpncalc.test.operator;

import com.airwallex.rpncalc.error.ExecutionException;
import com.airwallex.rpncalc.operator.impl.ClearOperator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClearOperatorTest extends OperatorTestBase {
    public ClearOperatorTest() {
        this.op = new ClearOperator();
    }

    @Test
    public void execute_ok() throws ExecutionException {
        presetCurrNumberStack("2.1 3.2");
        this.op.execute(this.rc);
        assertCurrNumberStack("");
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
        assertEquals("clear", op.getName());
    }

}
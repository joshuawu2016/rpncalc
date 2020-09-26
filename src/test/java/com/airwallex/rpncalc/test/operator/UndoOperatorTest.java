package com.airwallex.rpncalc.test.operator;

import com.airwallex.rpncalc.error.ExecutionException;
import com.airwallex.rpncalc.operator.IRpnCalcOperator;
import com.airwallex.rpncalc.operator.impl.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UndoOperatorTest extends OperatorTestBase {
    public UndoOperatorTest() {
        this.op = new UndoOperator();
    }

    @Test
    public void execute_ok() throws ExecutionException {
        presetCurrNumberStack("2.1 3.2");
        this.op.execute(this.rc);
        assertCurrNumberStack("2.1");
    }

    @Test
    public void execute_ok_undo_add() throws ExecutionException {
        presetCurrNumberStack("2.1 3.2");
        IRpnCalcOperator add = new AddOperator();
        add.execute(this.rc);
        this.op.execute(this.rc);
        assertCurrNumberStack("2.1 3.2");
    }

    @Test
    public void execute_ok_undo_sub() throws ExecutionException {
        presetCurrNumberStack("2.1 3.2");
        IRpnCalcOperator add = new SubtractOperator();
        add.execute(this.rc);
        this.op.execute(this.rc);
        assertCurrNumberStack("2.1 3.2");
    }

    @Test
    public void execute_ok_undo_mul() throws ExecutionException {
        presetCurrNumberStack("2.1 3.2");
        IRpnCalcOperator add = new MultiOperator();
        add.execute(this.rc);
        this.op.execute(this.rc);
        assertCurrNumberStack("2.1 3.2");
    }

    @Test
    public void execute_ok_undo_div() throws ExecutionException {
        presetCurrNumberStack("2.1 3.2");
        IRpnCalcOperator add = new AddOperator();
        add.execute(this.rc);
        this.op.execute(this.rc);
        assertCurrNumberStack("2.1 3.2");
    }

    @Test
    public void execute_ok_undo_sqrt() throws ExecutionException {
        presetCurrNumberStack("2.1 3.2");
        IRpnCalcOperator add = new SqrtOperator();
        add.execute(this.rc);
        this.op.execute(this.rc);
        assertCurrNumberStack("2.1 3.2");
    }

    @Test
    public void execute_ok_undo_clear() throws ExecutionException {
        presetCurrNumberStack("2.1 3.2");
        IRpnCalcOperator add = new ClearOperator();
        add.execute(this.rc);
        this.op.execute(this.rc);
        assertCurrNumberStack("2.1 3.2");
    }

    @Test
    public void getName2() {
        assertEquals("undo", op.getName());
    }

}
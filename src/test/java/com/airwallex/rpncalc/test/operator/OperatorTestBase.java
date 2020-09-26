package com.airwallex.rpncalc.test.operator;

import com.airwallex.rpncalc.ctx.RunningContext;
import com.airwallex.rpncalc.operator.IRpnCalcOperator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Scanner;

import static org.junit.Assert.*;

public class OperatorTestBase {
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    protected IRpnCalcOperator op = null;
    protected RunningContext rc = new RunningContext();

    @Before
    public void setUp2() {
        this.rc.reset();
    }

    protected void assertCurrNumberStack(String s) {
        Iterator<BigDecimal> it = this.rc.numberIterator();
        s = s.trim();
        if (s.length() == 0) {
            assertFalse(it.hasNext());
            return;
        }
        String[] s1 = s.split(" ");
        int i = 0;
        while (it.hasNext()) {
            BigDecimal real = it.next();
            BigDecimal expect = new BigDecimal(s1[i]);
            assertTrue(String.format("expect:%s not equal to real:%s", expect, real), expect.compareTo(real) == 0);
            i++;
        }
        assertEquals(s1.length, i);
    }

    protected void presetCurrNumberStack(String s) {
        try (Scanner sc = new Scanner(s)) {
            while (sc.hasNext()) {
                this.rc.pushNumber(new BigDecimal(sc.next()));
            }
        }
    }
}

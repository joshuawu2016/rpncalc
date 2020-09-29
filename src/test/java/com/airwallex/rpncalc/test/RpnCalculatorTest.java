package com.airwallex.rpncalc.test;

import com.airwallex.rpncalc.RpnCalculator;
import com.airwallex.rpncalc.error.ExecutionException;
import com.airwallex.rpncalc.operator.IRpnCalcOperator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RpnCalculatorTest {

    RpnCalculator calc = new RpnCalculator();
    List<String> realOutput = new ArrayList<>();

    public RpnCalculatorTest() {
        calc.setPostProcess(c -> {
            realOutput.add(c.getNumStackStatus());
        });

        calc.setOnExceptionProcess(c -> {
            IRpnCalcOperator calcOperator = c.getLastOperator();
            realOutput.add(String.format("%s %s (position: %d): %s", calcOperator.getType(), calcOperator.getName(), calcOperator.getPosition(), c.getErrorMessage()));
        });

    }

    @Before
    public void setUp2() {
        this.calc.reset();
        realOutput.clear();
    }

    @Test
    public void execute_ok() throws ExecutionException {
        doTestAndVerify(
                Arrays.asList(
                        "5 2",
                        "3 +"),
                Arrays.asList(
                        "stack: 5 2",
                        "stack: 5 5")
        );
    }

    @Test
    public void execute_ok1() throws ExecutionException {
        doTestAndVerify(
                Arrays.asList(
                        "2 sqrt",
                        "clear 9 sqrt"),
                Arrays.asList(
                        "stack: 1.4142135623",
                        "stack: 3")
        );
    }

    @Test
    public void execute_ok2() throws ExecutionException {
        doTestAndVerify(
                Arrays.asList(
                        "5 2 -",
                        "3 -",
                        "clear"),
                Arrays.asList(
                        "stack: 3",
                        "stack: 0",
                        "stack: ")
        );
    }

    @Test
    public void execute_ok3() throws ExecutionException {
        doTestAndVerify(
                Arrays.asList(
                        "5 4 3 2",
                        "undo undo *",
                        "5 *",
                        "undo"),
                Arrays.asList(
                        "stack: 5 4 3 2",
                        "stack: 20",
                        "stack: 100",
                        "stack: 20 5")
        );
    }

    @Test
    public void execute_ok4() throws ExecutionException {
        doTestAndVerify(
                Arrays.asList(
                        "7 12 2 /",
                        "*",
                        "4 /"),
                Arrays.asList(
                        "stack: 7 6",
                        "stack: 42",
                        "stack: 10.5")
        );
    }

    @Test
    public void execute_ok5() throws ExecutionException {
        doTestAndVerify(
                Arrays.asList(
                        "1 2 3 4 5",
                        "*",
                        "clear 3 4 -"),
                Arrays.asList(
                        "stack: 1 2 3 4 5",
                        "stack: 1 2 3 20",
                        "stack: -1")
        );
    }

    @Test
    public void execute_ok6() throws ExecutionException {
        doTestAndVerify(
                Arrays.asList(
                        "1 2 3 4 5",
                        "* * * *",
                        "clear 3 4 -"),
                Arrays.asList(
                        "stack: 1 2 3 4 5",
                        "stack: 120",
                        "stack: -1")
        );
    }

    @Test
    public void execute_insufficient_param1() throws ExecutionException {
        doTestAndVerify(
                Arrays.asList(
                        "1 2 3 * 5 + * * 6 5"
                ),
                Arrays.asList(
                        "operator * (position: 15): insucient parameters",
                        "stack: 11"
                )
        );
    }

    @Test
    public void execute_insufficient_param2() throws ExecutionException {
        doTestAndVerify(
                Arrays.asList(
                        "     ",
                        "+ /"
                ),
                Arrays.asList(
                        "operator + (position: 1): insucient parameters",
                        "stack: "
                )
        );
    }

    @Test
    public void execute_sqrt_negative() throws ExecutionException {
        doTestAndVerify(
                Arrays.asList(
                        "1 2 -2 sqrt"
                ),
                Arrays.asList(
                        "operator sqrt (position: 8): sqrt negative is not allowed",
                        "stack: 1 2 -2"
                )
        );
    }

    @Test
    public void execute_divide_zero() throws ExecutionException {
        doTestAndVerify(
                Arrays.asList(
                        "1 2 -2 + /"
                ),
                Arrays.asList(
                        "operator / (position: 10): zero divide",
                        "stack: 1 0"
                )
        );
    }
    private void doTestAndVerify(List<String> input, List<String> expectOutput) {
        for (String in : input) {
            calc.process(in);
            if (calc.getErrorMessage() != null)
                break;
        }
        Assert.assertArrayEquals(expectOutput.toArray(new String[0]), realOutput.toArray(new String[0]));
    }
}

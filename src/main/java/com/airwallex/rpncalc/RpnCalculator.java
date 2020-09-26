package com.airwallex.rpncalc;

import com.airwallex.rpncalc.ctx.RunningContext;
import com.airwallex.rpncalc.error.ExecutionException;
import com.airwallex.rpncalc.operator.IRpnCalcOperator;
import com.airwallex.rpncalc.operator.impl.*;

import java.util.function.Consumer;

public class RpnCalculator {
    Consumer<RpnCalculator> onExceptionProcess;
    Consumer<RpnCalculator> postProcess;
    String errorMessage;
    RunningContext rc = new RunningContext();
    IRpnCalcOperator calcOperator = null;

    public String getErrorMessage() {
        return errorMessage;
    }

    public IRpnCalcOperator getLastOperator() {
        return calcOperator;
    }

    public void reset() {
        rc.reset();
    }

    public void setPostProcess(Consumer<RpnCalculator> postProcess) {
        this.postProcess = postProcess;
    }

    public void setOnExceptionProcess(Consumer<RpnCalculator> onExceptionProcess) {
        this.onExceptionProcess = onExceptionProcess;
    }

    public String getNumStackStatus() {
        return rc.currentNumStackStr();
    }

    public void process(String line) {
        errorMessage = null;
        if (line == null || (line = line.trim()).length() == 0)
            return;

        StringBuilder sb = new StringBuilder();
        char[] cs = line.toCharArray();
        int i = 0;
        try {
            while (i < cs.length) {
                sb.setLength(0);
                while (i < cs.length && cs[i] == ' ') {
                    i++;
                }

                int pos = i;
                while (i < cs.length && cs[i] != ' ') {
                    sb.append(cs[i]);
                    i++;
                }

                String token = sb.toString();
                if (token == null || (token = token.trim()).length() == 0)
                    continue;

                calcOperator = decide(token);
                if (calcOperator != null) {
                    // pos output is 1 based.
                    calcOperator.setPosition(pos + 1);
                    calcOperator.execute(rc);
                }
            }
        } catch (ExecutionException e) {
            errorMessage = e.getErrorCode().getMsg();
            if (onExceptionProcess != null)
                onExceptionProcess.accept(this);
        } finally {
            if (postProcess != null) {
                postProcess.accept(this);
            }
        }
    }

    public IRpnCalcOperator decide(String token) {
        if ("clear".equalsIgnoreCase(token)) {
            return new ClearOperator();
        } else if ("undo".equalsIgnoreCase(token)) {
            return new UndoOperator();
        } else if ("sqrt".equalsIgnoreCase(token)) {
            return new SqrtOperator();
        } else if ("+".equalsIgnoreCase(token)) {
            return new AddOperator();
        } else if ("-".equalsIgnoreCase(token)) {
            return new SubtractOperator();
        } else if ("*".equalsIgnoreCase(token)) {
            return new MultiOperator();
        } else if ("/".equalsIgnoreCase(token)) {
            return new DivideOperator();
        }
        return new NumberOperator(token);
    }

}

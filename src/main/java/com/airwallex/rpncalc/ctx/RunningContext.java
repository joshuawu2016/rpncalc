package com.airwallex.rpncalc.ctx;

import com.airwallex.rpncalc.operator.IRpnCalcOperator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public class RunningContext {
    Deque<BigDecimal> current = new LinkedList<>();
    Deque<HistoryNode> history = new LinkedList<>();

    DecimalFormat df = new DecimalFormat("#.##########");

    public RunningContext() {
        df.setRoundingMode(RoundingMode.FLOOR);
    }

    public BigDecimal popNumber() {
        if (current.isEmpty())
            return null;
        return current.removeLast();
    }

    public int numStackSize() {
        return current.size();
    }

    public Iterator<BigDecimal> numberIterator() {
        return current.iterator();
    }

    public BigDecimal peekNumberStack() {
        if (current.isEmpty()) {
            return null;
        }
        return current.peekLast();
    }

    public void pushNumber(BigDecimal d) {
        current.addLast(d);
    }

    public boolean isHistoryStackEmpty() {
        return history.isEmpty();
    }

    public HistoryNode peekHistoryNode() {
        if (history.isEmpty()) {
            return null;
        }
        return history.peekLast();

    }

    public HistoryNode popHistoryNode() {
        if (history.isEmpty()) {
            return null;
        }
        return history.removeLast();
    }

    public void pushHistoryNode(IRpnCalcOperator c, BigDecimal... ds) {
        HistoryNode node = new HistoryNode();
        node.operator = c;
        for (BigDecimal d : ds) {
            node.numbers.addLast(d);
        }
        history.addLast(node);
    }

    public void pushHistoryNode(IRpnCalcOperator c, Deque<BigDecimal> d) {
        HistoryNode node = new HistoryNode();
        node.operator = c;
        node.numbers = d;
        history.addLast(node);

    }

    public String currentNumStackStr() {
        // construct number stack string
        StringBuilder sb = new StringBuilder();
        sb.append("stack: ");
        boolean hit = false;
        for (BigDecimal bd : current) {
            sb.append(df.format(bd));
            sb.append(' ');
            hit = true;
        }
        if (hit)
            sb.setLength(sb.length() - 1);
        return sb.toString();
    }


    public void reset() {
        this.current.clear();
        this.history.clear();
    }
}

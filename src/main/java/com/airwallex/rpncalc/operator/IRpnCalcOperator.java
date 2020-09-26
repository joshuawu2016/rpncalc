package com.airwallex.rpncalc.operator;

import com.airwallex.rpncalc.ctx.RunningContext;
import com.airwallex.rpncalc.error.ExecutionException;

public interface IRpnCalcOperator {
    // +,-,*,/
    //    get two numbers from current number stack
    //    calculate, push back result
    //    construct history node(two numbers + operator), push to history node stack

    // sqrt
    //    get one numbers from current number stack
    //    calculate, push back result
    //    construct history node(one number + operator), push to history node stack

    // clear
    //    pop all numbers from current number stack
    //    construct history node(all numbers + operator), push to history node stack

    // undo
    //    if no history node in stack, pop one number from current number stack
    //    if previous history node is +, -, *, /, sqrt,
    //        pop one number from current number stack
    //        pop one history node from history node stack, push into current number stack
    //    if previous history node is clear
    //        pop one history node from history node stack, push into current number stack

    void execute(RunningContext ctx) throws ExecutionException;

    void undo(RunningContext ctx);

    String getName();

    int getPosition();

    void setPosition(int i);

    default String getType() {
        return "operator";
    }
}

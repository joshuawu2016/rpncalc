package com.airwallex.rpncalc.error;

public enum ErrorCode {
    InternalError(0, "internal error"),
    InsufficientParams(1, "insucient parameters"),
    ZeroDivide(2, "zero divide"),
    NegativeSqrt(3, "sqrt negative is not allowed"),
    NumberNotValid(4, "can not parse as number"),

    ;

    int id;
    String msg;

    ErrorCode(int i, String j) {
        id = i;
        msg = j;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

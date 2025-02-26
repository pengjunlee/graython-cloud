package com.pengjunlee.demo.common.biz;

import gray.bingo.common.exceptions.BingoException;

public class BIZException extends BingoException {



    public BIZException(BIZExceptionCodeEnum errorCodeEnum) {
        super(errorCodeEnum);
    }

    public String toString() {
        return "BIZException{ code : " + this.getCode() + ", message : " + this.getMessage() + "}";
    }
}

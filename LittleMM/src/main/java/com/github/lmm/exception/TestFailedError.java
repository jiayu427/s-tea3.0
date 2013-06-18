package com.github.lmm.exception;

/**
 * @author 王天庆
 * */
public class TestFailedError extends RuntimeException {
    public TestFailedError(){
        super();
    }

    public TestFailedError(String message){
        super(message);
    }
    public TestFailedError(String message,Exception e){
        super(message,e);
    }
}

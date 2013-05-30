package com.github.lmm.exception;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-30
 * Time: 下午1:46
 * To change this template use File | Settings | File Templates.
 */
public class SameIDSourceError extends RuntimeException{
    public SameIDSourceError(){
        super();
    }
    public SameIDSourceError(String message){
        super(message);
    }
    public SameIDSourceError(String message,Exception e){
        super(message,e);
    }
}

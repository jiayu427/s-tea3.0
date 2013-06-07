package com.github.lmm.exception;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-6-6
 * Time: 下午3:26
 * To change this template use File | Settings | File Templates.
 */
public class ConfigWrongException extends RuntimeException {
    public ConfigWrongException(){
        super();
    }

    public ConfigWrongException(String message){
        super(message);
    }

    public ConfigWrongException(String message,Exception e){
        super(message,e);
    }
}

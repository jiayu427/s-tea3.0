package com.github.lmm.exception;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-31
 * Time: 下午2:01
 * To change this template use File | Settings | File Templates.
 */
public class NotExistLocatorError extends RuntimeException {
    public NotExistLocatorError(){
        super();
    }

    public NotExistLocatorError(String message){
        super(message);
    }

    public NotExistLocatorError(String message,Exception e){
        super(message,e);
    }
}

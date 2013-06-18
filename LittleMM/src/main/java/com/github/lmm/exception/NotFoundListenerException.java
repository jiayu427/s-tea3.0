package com.github.lmm.exception;

/**
 * @author 王天庆
 * */
public class NotFoundListenerException extends RuntimeException {
    public NotFoundListenerException(){
        super();
    }
    public NotFoundListenerException(String message){
        super(message);
    }

    public NotFoundListenerException(String message,Exception e){
        super(message,e);
    }
}

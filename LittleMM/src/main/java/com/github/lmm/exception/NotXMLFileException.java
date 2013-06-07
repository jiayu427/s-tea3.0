package com.github.lmm.exception;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-6-7
 * Time: 下午1:11
 * To change this template use File | Settings | File Templates.
 */
public class NotXMLFileException extends RuntimeException {

    public NotXMLFileException(){
        super();
    }

    public NotXMLFileException(String message){
        super(message);
    }

    public NotXMLFileException(String message,Exception e){
        super(message,e);
    }
}

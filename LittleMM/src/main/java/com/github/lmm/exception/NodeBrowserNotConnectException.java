package com.github.lmm.exception;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-6-6
 * Time: 下午3:35
 * To change this template use File | Settings | File Templates.
 */
public class NodeBrowserNotConnectException extends  RuntimeException {
    public NodeBrowserNotConnectException(){
        super();
    }
    public NodeBrowserNotConnectException(String message){
        super(message);
    }

    public NodeBrowserNotConnectException(String message,Exception e){
        super(message,e);
    }
}

package com.github.lmm.exception;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-6-26
 * Time: 上午11:33
 * To change this template use File | Settings | File Templates.
 */
public class MethodParameterNotMatchError extends RuntimeException {
    public MethodParameterNotMatchError(){
        super();
    }

    public MethodParameterNotMatchError(String message){
        super(message);
    }
    public MethodParameterNotMatchError(String message,Exception e){
        super(message,e);
    }
}

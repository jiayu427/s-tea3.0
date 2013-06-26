package com.github.lmm.runner;

import org.databene.commons.ArrayFormat;
import org.junit.runners.model.FrameworkMethod;

import java.lang.reflect.Method;

/**
 * @author  ouamaqing
 *
 */
public class FrameworkMethodWithPict extends FrameworkMethod {
    protected Object[] parameters;
    protected String info;
    public FrameworkMethodWithPict(Method method,Object[] params) {
        super(method);
        this.parameters=params;
        this.info= formatInfo();
    }

    @Override
    public synchronized Object invokeExplosively(Object target, Object... parameters) throws Throwable {
        return super.invokeExplosively(target, this.parameters);
    }

    @Override
    public String toString() {
        return getMethod().getName() + '<' + info + '>';
    }

    public String formatInfo(){
        String info="";
        for(Object param:this.parameters){
            info=info+param+",";
        }
        return info.substring(0,info.length()-1);
    }
}

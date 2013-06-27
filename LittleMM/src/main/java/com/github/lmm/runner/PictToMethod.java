package com.github.lmm.runner;

import com.github.lmm.commons.StringUtils;
import com.github.lmm.pairwise.generator.ExcelParameters;
import com.github.lmm.pairwise.generator.LineParameters;
import com.github.lmm.pairwise.generator.Parameters;
import com.github.lmm.pairwise.generator.TXTParameters;
import com.github.lmm.runner.info.DefaultInfoProvider;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author  ouamaqing
 * Date: 13-6-26
 * Time: 上午10:47
 * To change this template use File | Settings | File Templates.
 */
public class PictToMethod {
    private Parameters parameters;
    //private Method method;
    public PictToMethod(File f){
        if(f.getName().endsWith(".xls")){
            this.parameters=new ExcelParameters(f);
            parameters.generate();
        }else if(f.getName().endsWith(".txt")){
            this.parameters=new TXTParameters(f);
            parameters.generate();
        }
    }

    public PictToMethod(Parameters parameters){
        this.parameters=parameters;
    }

    public PictToMethod(String path){
        this(new File(path));
    }

    public List<FrameworkMethodWithParameters> generatorPICTMethod(Method method){
        DefaultInfoProvider infoProvider=new DefaultInfoProvider();
        List<FrameworkMethodWithParameters> result = new ArrayList<FrameworkMethodWithParameters>();
        for(LineParameters lineParameters:getParams()){
            Object[] params = new Object[lineParameters.size()];
            int i=0;
            for(String string:lineParameters.getParameters()){
                Class<?> clazz = getMethodParameterTypes(method)[i];
                if(clazz.getName().endsWith("Integer")){
                    if(string.contains(".")){
                        string=string.substring(0,string.indexOf("."));
                    }
                    params[i]= StringUtils.toInteger(string);
                }else if(clazz.getName().endsWith("Boolean")){
                    params[i]=StringUtils.toBoolean(string);
                }else if(clazz.getName().endsWith("String")){
                    if(string.equals("<empty>")){
                        params[i]="";
                    }else if(string.equals("<null>")){
                        params[i]=null;
                    }else{
                        params[i]=string;
                    }
                }else if(clazz.getName().endsWith("Double")){
                    params[i]=StringUtils.toDouble(string);
                }else if(clazz.getName().endsWith("Float")){
                    params[i]=StringUtils.toFloat(string);
                }else if(clazz.getName().endsWith("Long")){
                    params[i]=StringUtils.toLong(string);
                }else{
                    throw new UnsupportedOperationException("暂时不支持非基础属性的参数形式，支持String,Integer,Boolean," +
                            "Double,Float,Long");
                }
                i++;
            }
            FrameworkMethodWithParameters fm=new FrameworkMethodWithParameters(method,params,infoProvider.testInfo(method,params));
            result.add(fm);

        }
        return result;
    }


    public List<LineParameters> getParams(){

        return this.parameters.getParameters();
    }

    public Class<?>[] getMethodParameterTypes(Method method){
        Class<?>[] types= method.getParameterTypes();
        return types;
    }
}


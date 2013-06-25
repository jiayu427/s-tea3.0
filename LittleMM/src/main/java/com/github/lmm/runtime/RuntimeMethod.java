package com.github.lmm.runtime;

import java.lang.reflect.Method;
import org.junit.Test;
/**
 * @author 王天庆
 * */
public class RuntimeMethod {
    static ThreadLocal<String> mname = new ThreadLocal<String>(){
        String name="Main";
        protected String initialValue() {
            return name;
        };
    };
    public static String getMethodName(){
        StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
        for(int i=0;i<stacks.length;i++){
            //System.out.println(stacks[i].getClassName());
            try {
                Class<?> clazz=Class.forName(stacks[i].getClassName());
                Method[] methods=clazz.getDeclaredMethods();
                for(Method m:methods){
                    if(m.getName().equals(stacks[i].getMethodName())){
                        if(m.isAnnotationPresent(Test.class)||m.isAnnotationPresent(org.testng.annotations.Test.class)){
                            String className = stacks[i].getClassName();
                            mname.set("Case:"+className.substring(className.lastIndexOf(".")+1, className.length())+"=>"+stacks[i].getMethodName());
                            return mname.get();
                        }
                    }
                }
                i++;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String getName(){
        return mname.get();
    }

    public static void setName(String name){
        mname.set(name);
    }
}

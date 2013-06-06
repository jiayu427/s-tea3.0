package com.github.lmm.runtime;

import java.lang.reflect.Method;
import org.junit.Test;
/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-31
 * Time: 下午2:48
 * To change this template use File | Settings | File Templates.
 */
public class RuntimeMethod {
    static ThreadLocal<String> mname = new ThreadLocal<String>(){
        String name;
        protected String initialValue() {
            return name;
        };

        @SuppressWarnings("unused")
        protected void setName(String string){
            this.name=string;
        }
    };
    //static String name="Main";
    public static String getMethodName(){
        StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
        for(int i=0;i<stacks.length;i++){
            try {
                Class<?> clazz=Class.forName(stacks[i].getClassName());
                Method[] methods=clazz.getDeclaredMethods();
                for(Method m:methods){
                    if(m.getName().equals(stacks[i].getMethodName())){
                        if(m.isAnnotationPresent(Test.class)||m.getName().toLowerCase().equals("main")||
                                m.isAnnotationPresent(org.testng.annotations.Test.class)){
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
}

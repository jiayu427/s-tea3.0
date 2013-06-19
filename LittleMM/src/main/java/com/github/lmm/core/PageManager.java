package com.github.lmm.core;

import com.github.lmm.annotation.Commit;
import com.github.lmm.intrumentation.ClassPool;
import com.github.lmm.page.ICurrentPage;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-6-13
 * Time: 下午1:37
 * To change this template use File | Settings | File Templates.
 */
public class PageManager {
    private static Map<String,Class<?>> pageMap=new HashMap<String, Class<?>>();
    public static void collectPageInfomation(){
        if(pageMap.size()!=0){
            Set<Class<?>> cls= ClassPool.getClassPool();
            for(Class<?>clazz:cls){
                if(clazz.isAnnotationPresent(Commit.class)){
                    pageMap.put(clazz.getAnnotation(Commit.class).value(),clazz);
                }
            }
        }
    }

    public static Class<?> getPageClass(String commit){
        return pageMap.get(commit);
    }


}

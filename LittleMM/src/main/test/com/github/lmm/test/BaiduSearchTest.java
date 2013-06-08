package com.github.lmm.test;
import com.github.lmm.intrumentation.ClassPool;
import org.junit.Test;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-6-8
 * Time: 下午2:09
 * To change this template use File | Settings | File Templates.
 */
public class BaiduSearchTest {

    @Test
    public void test(){
        Set<Class<?>> cls = ClassPool.getClassPool();
        for(Class<?>clazz:cls){
            System.out.println("扫描");
            System.out.println(clazz.getName());
        }
    }
}

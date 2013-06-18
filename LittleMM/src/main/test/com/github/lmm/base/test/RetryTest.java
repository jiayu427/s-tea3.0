package com.github.lmm.base.test;

import com.github.lmm.annotation.Retry;
import com.github.lmm.annotation.ThreadRunner;
import com.github.lmm.runner.JUnitBaseRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-6-18
 * Time: 下午2:20
 * To change this template use File | Settings | File Templates.
 */
@RunWith(JUnitBaseRunner.class)
@ThreadRunner(threads = 1)
@Retry(2)
public class RetryTest {

    @Test
    public void testRetry(){
        System.out.println("Hello World");
        //throw new RuntimeException("这是一个测试类的异常");
    }
}

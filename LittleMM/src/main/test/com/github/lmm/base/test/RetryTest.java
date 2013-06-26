package com.github.lmm.base.test;

import com.github.lmm.annotation.Pict;
import com.github.lmm.annotation.Retry;
import com.github.lmm.annotation.ThreadRunner;
import com.github.lmm.browser.Browser;
import com.github.lmm.core.Auto;
import com.github.lmm.runner.JUnitBaseRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;

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


    @Test
    @Pict("test.txt")
    public void pictTest(Integer dianshi,String bingxiang){
        Auto.require(Browser.PhantomJS);
        Auto.open("http://www.baidu.com");
        Auto.currentage().element(By.id("kw")).input(dianshi+bingxiang);
        Auto.currentage().element(By.id("su")).click();
        Auto.closeAllWindows();
    }
}

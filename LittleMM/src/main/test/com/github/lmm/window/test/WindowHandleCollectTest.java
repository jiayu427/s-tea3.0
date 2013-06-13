package com.github.lmm.window.test;

import com.github.lmm.annotation.ThreadRunner;
import com.github.lmm.browser.Browser;
import com.github.lmm.core.Auto;
import com.github.lmm.runner.JUnitBaseRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-6-13
 * Time: 下午4:02
 * To change this template use File | Settings | File Templates.
 */
@RunWith(JUnitBaseRunner.class)
@ThreadRunner(threads = 1)
public class WindowHandleCollectTest {

    @Test
    public void  windowHandleTest(){
        Auto.require(Browser.PhantomJS);
        Auto.open("http://www.hao123.com");
        Auto.currentage().openNewWindow("http://www.baidu.com");
        Assert.assertEquals(Auto.browser().getWindows().size(), 2);
        Auto.currentage().element().addLocator(By.id("kw"),0).input("北京");
        Auto.currentage().element().addLocator(By.id("su")).click();
        Auto.closeAllWindows();
    }
}

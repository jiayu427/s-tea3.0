package com.github.lmm.test;
import com.github.lmm.annotation.Browsers;
import com.github.lmm.annotation.ThreadRunner;
import com.github.lmm.browser.Browser;
import com.github.lmm.core.Auto.*;
import com.github.lmm.core.Auto;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.github.lmm.runner.JUnitBaseRunner;
/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-30
 * Time: 上午9:36
 * To change this template use File | Settings | File Templates.
 */

@RunWith(JUnitBaseRunner.class)
@ThreadRunner(threads = 1)
public class BrowserTest {

    @Test
    public void test(){
        Firefox.open("http://www.baidu.com") ;
        Firefox.closeAllWindows();
    }
    @Test
    @Browsers({Browser.FIREFOX,Browser.HTMLUNIT})
    public void testone(){
        Auto.open("http://www.baidu.com");
        Auto.closeAllWindows();
    }
    @Test
    public void testtow(){
        Auto.require(Browser.FIREFOX);
        Auto.open("http://www.baidu.com");
        Auto.closeAllWindows();
    }
    @Test
    public void testthree(){
        Auto.require("firefox");
        Auto.open("http://www.baidu.com");
        Auto.closeAllWindows();
    }




}

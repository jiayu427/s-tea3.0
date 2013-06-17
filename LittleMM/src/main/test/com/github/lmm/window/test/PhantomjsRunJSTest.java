package com.github.lmm.window.test;

import com.github.lmm.annotation.ThreadRunner;
import com.github.lmm.browser.Browser;
import com.github.lmm.core.Auto;
import com.github.lmm.runner.JUnitBaseRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-6-14
 * Time: 下午3:08
 * To change this template use File | Settings | File Templates.
 */
@RunWith(JUnitBaseRunner.class)
@ThreadRunner(threads = 1)
public class PhantomjsRunJSTest {

    @Test
    public void runjsTest(){
        Auto.require(Browser.PhantomJS);
        Auto.open("http://www.baidu.com");
        Auto.browser().runJavaScript("alert(\"hello,world\")");
        Auto.closeAllWindows();
    }
}

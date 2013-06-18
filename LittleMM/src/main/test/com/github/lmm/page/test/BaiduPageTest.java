package com.github.lmm.page.test;

import com.github.lmm.annotation.ThreadRunner;
import com.github.lmm.browser.Browser;
import com.github.lmm.core.Auto;
import com.github.lmm.runner.JUnitBaseRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-6-17
 * Time: 下午5:15
 * To change this template use File | Settings | File Templates.
 */
@RunWith(JUnitBaseRunner.class)
@ThreadRunner(threads = 1)
public class BaiduPageTest {

    @Test
    public void testpage(){
        Auto.require(Browser.FIREFOX);
        Auto.open("http://www.baidu.com");
        Auto.page(BaiduPage.class).search();
        Auto.closeAllWindows();
    }
}

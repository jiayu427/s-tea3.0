package com.github.lmm.element.test;

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
 * Date: 13-6-17
 * Time: 上午11:29
 * To change this template use File | Settings | File Templates.
 */
@RunWith(JUnitBaseRunner.class)
@ThreadRunner(threads = 1)
public class ElementFrameFunctionTest {

    @Test
    public void frameFunctionTest(){
        Auto.require(Browser.FIREFOX);
        Auto.open("http://product.it168.com/list/b/0301_1.shtml");
        //Auto.browser().selectFrame(0);
        //Auto.browser().selectFrame(0);
        Auto.currentage().element().addLocator(By.xpath(".//*[@id='title4']")).click();
        Auto.closeAllWindows();
    }
}

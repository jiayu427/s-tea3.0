package com.github.lmm.test;

import com.github.lmm.browser.Browser;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-30
 * Time: 上午9:36
 * To change this template use File | Settings | File Templates.
 */
public class BrowserTest {
   @Test
    public void browsertest(){
        WebDriver driver = Browser.Firefox.browser();
        driver.close();
   }
}

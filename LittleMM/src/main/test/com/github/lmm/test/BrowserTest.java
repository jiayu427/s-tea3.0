package com.github.lmm.test;

import com.github.lmm.browser.BaseBrowser;
import com.github.lmm.browser.Browser;
import com.github.lmm.element.Locator;
import com.github.lmm.page.ICurrentPage;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;
import com.github.lmm.core.Auto;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-30
 * Time: 上午9:36
 * To change this template use File | Settings | File Templates.
 */
public class BrowserTest {

    @Test
    public void test(){
        PropertyConfigurator.configure("resource/log4j.properties");
        Auto.Firefox.open("http://www.baidu.com");
        Auto.Firefox.currentage().element(By.id("kw")).input("北京");
        Auto.Firefox.currentage().element(By.id("su")).click();
        Auto.Firefox.closeAllWindows();
        Auto.Chrome.open("http://www.baidu.com");
        Auto.Chrome.currentage().element("百度首页-搜索框").input("北京");
        Auto.Chrome.currentage().element().addLocator(Locator.ID,"su").click();
        Auto.Chrome.closeAllWindows();
    }





}

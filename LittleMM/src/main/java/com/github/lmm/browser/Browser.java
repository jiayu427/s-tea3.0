package com.github.lmm.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-29
 * Time: 下午5:39
 * To change this template use File | Settings | File Templates.
 */
public enum Browser {
    IE(){
        public WebDriver createBrowser(){
                  return new InternetExplorerDriver();
        }
    },
    Firefox(){

    },
    Chrome(),
    Safari(),
    Opera(),
    HtmlUnit(),

}

package com.github.lmm.browser;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.opera.core.systems.OperaDriver;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-29
 * Time: 下午5:39
 * To change this template use File | Settings | File Templates.
 */
public enum Browser {
    IE(){
        public WebDriver browser(){
                  return new InternetExplorerDriver();
        }
        public RemoteWebDriver browser(URL url){
            return new RemoteWebDriver(url,DesiredCapabilities.internetExplorer());
        }
    },
    Firefox(){
        public WebDriver browser(){
              return new FirefoxDriver();
          }
        public RemoteWebDriver browser(URL url){
            return new RemoteWebDriver(url,DesiredCapabilities.firefox());
        }
    },
    Chrome(){
        public WebDriver browser(){
                  return new ChromeDriver();
        }
        public RemoteWebDriver browser(URL url){
            return new RemoteWebDriver(url,DesiredCapabilities.chrome());
        }
    },
    Safari(){
        public WebDriver browser(){
            return new SafariDriver();
        }
        public RemoteWebDriver browser(URL url){
            return new RemoteWebDriver(url,DesiredCapabilities.safari());
        }
    },
    Opera(){
        public WebDriver browser(){
            return new OperaDriver();
        }
        public RemoteWebDriver browser(URL url){
            return new RemoteWebDriver(url,DesiredCapabilities.opera());
        }
    },
    HtmlUnit(){
        public WebDriver browser(){
            HtmlUnitDriver driver = new HtmlUnitDriver(BrowserVersion.FIREFOX_17);
            driver.setJavascriptEnabled(true);
            return driver;
        }
        public RemoteWebDriver browser(URL url){
            RemoteWebDriver driver= new RemoteWebDriver(url,DesiredCapabilities.htmlUnit(),new DesiredCapabilities(){
                @Override
                public boolean isJavascriptEnabled() {
                    return true;
                }
            });
            return driver;
        }
    };

    public WebDriver browser(){
        return new FirefoxDriver();
    }

    public RemoteWebDriver browser(URL url){
        return new RemoteWebDriver(url, DesiredCapabilities.firefox());
    }

}

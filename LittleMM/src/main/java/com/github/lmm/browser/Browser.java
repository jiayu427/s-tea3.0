package com.github.lmm.browser;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.opera.core.systems.OperaDriver;
import java.net.URL;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;


/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-29
 * Time: 下午5:39
 * To change this template use File | Settings | File Templates.
 */
public enum Browser{
    PhantomJS(){
        @Override
        protected PhantomJSDriver browser() {
            return new PhantomJSDriver(new DesiredCapabilities());
        }

        @Override
        protected RemoteWebDriver browser(URL url) {
            return new RemoteWebDriver(url,DesiredCapabilities.phantomjs());
        }
    },
    IE(){
        public InternetExplorerDriver browser(){
            return new InternetExplorerDriver();
        }
        public RemoteWebDriver browser(URL url){
            return new RemoteWebDriver(url,DesiredCapabilities.internetExplorer());
        }
    },
    FIREFOX(){
        public FirefoxDriver browser(){
              return new FirefoxDriver();
          }
        public RemoteWebDriver browser(URL url){
            return new RemoteWebDriver(url,DesiredCapabilities.firefox());
        }
    },
    CHROME(){
        public ChromeDriver browser(){
                  return new ChromeDriver();
        }
        public RemoteWebDriver browser(URL url){
            return new RemoteWebDriver(url,DesiredCapabilities.chrome());
        }
    },
    SAFARI(){
        public SafariDriver browser(){
            return new SafariDriver();
        }
        public RemoteWebDriver browser(URL url){
            return new RemoteWebDriver(url,DesiredCapabilities.safari());
        }
    },
    OPERA(){
        public OperaDriver browser(){
            return new OperaDriver();
        }
        public RemoteWebDriver browser(URL url){
            return new RemoteWebDriver(url,DesiredCapabilities.opera());
        }
    },
    HTMLUNIT(){
        public HtmlUnitDriver browser(){
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
    },
    HTMLUNIT_FIRFOR_3_6(){
        @Override
        protected HtmlUnitDriver browser() {
            HtmlUnitDriver driver = new HtmlUnitDriver((BrowserVersion.FIREFOX_3_6));
            driver.setJavascriptEnabled(true);
            return driver;
        }

        @Override
        protected RemoteWebDriver browser(URL url) {
            RemoteWebDriver driver= new RemoteWebDriver(url,DesiredCapabilities.htmlUnit(),new DesiredCapabilities(){
                @Override
                public boolean isJavascriptEnabled() {
                    return true;
                }
            });
            return driver;
        }
    },
    HTMLUNIT_FIRFOX_10(){
        @Override
        protected HtmlUnitDriver browser() {
            HtmlUnitDriver driver = new HtmlUnitDriver((BrowserVersion.FIREFOX_10));
            driver.setJavascriptEnabled(true);
            return driver;
        }

        @Override
        protected RemoteWebDriver browser(URL url) {
            RemoteWebDriver driver= new RemoteWebDriver(url,DesiredCapabilities.htmlUnit(),new DesiredCapabilities(){
                @Override
                public boolean isJavascriptEnabled() {
                    return true;
                }
            });
            return driver;
        }
    },
    HTMLUNIT_CHROME(){
        @Override
        protected HtmlUnitDriver browser() {
            HtmlUnitDriver driver = new HtmlUnitDriver((BrowserVersion.CHROME));
            driver.setJavascriptEnabled(true);
            return driver;
        }

        @Override
        protected RemoteWebDriver browser(URL url) {
            RemoteWebDriver driver= new RemoteWebDriver(url,DesiredCapabilities.htmlUnit(),new DesiredCapabilities(){
                @Override
                public boolean isJavascriptEnabled() {
                    return true;
                }
            });
            return driver;
        }
    },
    HTMLUNIT_CHROME_16(){
        @Override
        protected HtmlUnitDriver browser() {
            HtmlUnitDriver driver = new HtmlUnitDriver((BrowserVersion.CHROME_16));
            driver.setJavascriptEnabled(true);
            return driver;
        }

        @Override
        protected RemoteWebDriver browser(URL url) {
            RemoteWebDriver driver= new RemoteWebDriver(url,DesiredCapabilities.htmlUnit(),new DesiredCapabilities(){
                @Override
                public boolean isJavascriptEnabled() {
                    return true;
                }
            });
            return driver;
        }
    },
    HTMLUNIT_INTERNET_EXPLORER_6{
        @Override
        protected HtmlUnitDriver browser() {
            HtmlUnitDriver driver = new HtmlUnitDriver((BrowserVersion.INTERNET_EXPLORER_6));
            driver.setJavascriptEnabled(true);
            return driver;
        }

        @Override
        protected RemoteWebDriver browser(URL url) {
            RemoteWebDriver driver= new RemoteWebDriver(url,DesiredCapabilities.htmlUnit(),new DesiredCapabilities(){
                @Override
                public boolean isJavascriptEnabled() {
                    return true;
                }
            });
            return driver;
        }
    },
    HTMLUNIT_INTERNET_EXPLORER_7{
        @Override
        protected HtmlUnitDriver browser() {
            HtmlUnitDriver driver = new HtmlUnitDriver((BrowserVersion.INTERNET_EXPLORER_7));
            driver.setJavascriptEnabled(true);
            return driver;
        }

        @Override
        protected RemoteWebDriver browser(URL url) {
            RemoteWebDriver driver= new RemoteWebDriver(url,DesiredCapabilities.htmlUnit(),new DesiredCapabilities(){
                @Override
                public boolean isJavascriptEnabled() {
                    return true;
                }
            });
            return driver;
        }
    },
    HTMLUNIT_INTERNET_EXPLORER_8{
        @Override
        protected HtmlUnitDriver browser() {
            HtmlUnitDriver driver = new HtmlUnitDriver((BrowserVersion.INTERNET_EXPLORER_8));
            driver.setJavascriptEnabled(true);
            return driver;
        }

        @Override
        protected RemoteWebDriver browser(URL url) {
            RemoteWebDriver driver= new RemoteWebDriver(url,DesiredCapabilities.htmlUnit(),new DesiredCapabilities(){
                @Override
                public boolean isJavascriptEnabled() {
                    return true;
                }
            });
            return driver;
        }
    },
    HTMLUNIT_INTERNET_EXPLORER_9{
        @Override
        protected HtmlUnitDriver browser() {
            HtmlUnitDriver driver = new HtmlUnitDriver((BrowserVersion.INTERNET_EXPLORER_9));
            driver.setJavascriptEnabled(true);
            return driver;
        }

        @Override
        protected RemoteWebDriver browser(URL url) {
            RemoteWebDriver driver= new RemoteWebDriver(url,DesiredCapabilities.htmlUnit(),new DesiredCapabilities(){
                @Override
                public boolean isJavascriptEnabled() {
                    return true;
                }
            });
            return driver;
        }
    };

    protected abstract <T> T browser();

    protected abstract RemoteWebDriver browser(URL url);

}

package com.github.lmm.browser;

import com.gargoylesoftware.htmlunit.BrowserVersion;

import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-29
 * Time: 上午11:03
 * To change this template use File | Settings | File Templates.
 */
public class HtmlUnit extends HttpBrowser {
    private BrowserVersion version;
    private URL url;
    public HtmlUnit(URL url) {
        this.url=url;
        this.version=BrowserVersion.FIREFOX_17;
    }

    public HtmlUnit(URL url, BrowserVersion browserVersion) {
        this.url=url;
        this.version=browserVersion;
    }
}

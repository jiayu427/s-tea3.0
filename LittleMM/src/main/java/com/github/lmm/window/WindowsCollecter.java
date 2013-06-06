package com.github.lmm.window;

import com.github.lmm.browser.IBrowser;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-30
 * Time: 下午3:37
 * To change this template use File | Settings | File Templates.
 */
public class WindowsCollecter extends EventObject {
    private Logger logger = Logger.getLogger(WindowsCollecter.class);
    public Set<String> windowhandles;
    public int windowNums;
    private Map<String,WindowInfo> windowInfoMap;
    private List<WindowInfo> windowInfoList;
    private Map<String,WindowInfo> windowInfourlMap;
    private IBrowser browser;
    public WindowsCollecter(Object source,IBrowser browser) {
        super(source);
        this.browser=browser;
        this.windowInfoMap=new HashMap<String, WindowInfo>();
        this.windowInfourlMap = new HashMap<String,WindowInfo>();
        this.windowInfoList=new ArrayList<WindowInfo>();
        this.windowNums=browser.getWindows().size();
        this.windowhandles=browser.getWindows();
        logger.info("当前初始化页面信息：URL--->"+this.browser.getCurrentPage().getUrl());
        logger.info("当前初始化页面信息：Title--->"+this.browser.getCurrentPage().getTitle());
        logger.info("当前初始化页面信息：窗口句柄数--->"+this.browser.getWindows().size());
    }

    public void updateWindows(){
        if(browser.getWindows().size()-this.windowNums>1){
            logger.warn("在测试过程中出现了同时打开多个页面的情况，页面收集器将对页面的索引查找功能可能会出现混乱，谨慎使用通过index来切换页面。");
        }
        Set<String> handles=browser.getWindows();
        if(handles.size()>this.windowNums){
            logger.info("窗口的句柄数增多，进行句柄收集操作");
            String currentWindowHandle=browser.getCurrentBrowserDriver().getWindowHandle();
            for(String windowhandle:windowhandles){
                if(this.windowhandles.contains(windowhandle)){
                    continue;
                }else{
                    browser.getCurrentBrowserDriver().switchTo().window(windowhandle);
                    String url=browser.getCurrentPage().getUrl();
                    String title=browser.getCurrentPage().getTitle();
                    WindowInfo windowInfo = new WindowInfo(browser,url,windowhandle,title);
                    windowInfoMap.put(title,windowInfo);
                    windowInfourlMap.put(url,windowInfo);
                    windowInfoList.add(windowInfo);
                    this.windowNums=handles.size();
                    logger.info("添加了新的窗口信息->"+title);
                }

            }
            browser.getCurrentBrowserDriver().switchTo().window(currentWindowHandle);
        }else if(handles.size()<this.windowNums){
            String currentWindowHandle=browser.getCurrentBrowserDriver().getWindowHandle();
            for(String windowhandle:this.windowhandles){
                if(handles.contains(windowhandle)){
                    continue;
                }else{
                    browser.getCurrentBrowserDriver().switchTo().window(windowhandle);
                    String title=browser.getCurrentPage().getTitle();
                    windowInfoList.remove(windowInfoMap.get(title));
                    windowInfoMap.remove(title);
                    windowInfourlMap.remove(browser.getCurrentPage().getUrl());
                    this.windowNums=handles.size();
                    logger.info("更新了窗口信息，窗口->"+title+"被删除了");
                }
            }
            browser.getCurrentBrowserDriver().switchTo().window(currentWindowHandle);
        }

    }

    public Map<String,WindowInfo> getWindowInfoMap(){
        return this.windowInfoMap;
    }

    public String getLastWindowhandle(){
        WindowInfo windowInfo=this.windowInfoList.get(this.windowNums-1);
        return windowInfo.getWindowHandle();
    }

    public String getFirstWindowhandle(){
        WindowInfo windowInfo = this.windowInfoMap.get(0);
        return windowInfo.getWindowHandle();
    }

    public String getWindowhandleByIndex(Integer index){
        WindowInfo windowInfo=this.windowInfoList.get(index-1);
        return windowInfo.getWindowHandle();
    }



}

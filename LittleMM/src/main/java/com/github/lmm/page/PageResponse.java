package com.github.lmm.page;

import com.google.common.net.HttpHeaders;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import java.util.*;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-6-5
 * Time: 下午1:51
 * To change this template use File | Settings | File Templates.
 */
public class PageResponse {
    private HttpClient httpClient;
    private HttpGet httpGet;
    private ICurrentPage page;
    private HttpResponse response;
    public PageResponse(ICurrentPage page){
        this.page=page;
        this.httpClient=new DefaultHttpClient();
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,60000);
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,60000);
        httpGet=new HttpGet(this.page.getUrl());
        this.httpGet.addHeader(HttpHeaders.ACCEPT_ENCODING,"gzip,deflate");
        try {
            this.response=httpClient.execute(httpGet);
        } catch (IOException e) {
            throw new RuntimeException("获取网络连接的时候出现了错误，没有获取到响应！");
        }
    }

    public Header[] getHeaders(){
        Header[] headers=this.response.getAllHeaders();
        return headers;
    }

    public Map<String,String> getHeaderMap(){
        Map<String,String> headermap=new HashMap<String,String>();
        for(Header header:getHeaders()){
            headermap.put(header.getName(),header.getValue());
        }
        return headermap;
    }

    public List<String> getJavaScriptURL(){
        List<String> jslist=new ArrayList<String>();
        Document document= Jsoup.parse(this.page.getPageSource());
        Elements elements = document.select("script");
        Iterator<Element> iter = elements.iterator();
        while(iter.hasNext()){
            Element element=iter.next();
            jslist.add(element.attr("src"));
        }
        return jslist;
    }

    public List<String> getAllCssUrlByLinked(){
        List<String> csslist=new ArrayList<String>();
        Document document= Jsoup.parse(this.page.getPageSource());
        Elements elements = document.select("link");
        Iterator<Element> iter = elements.iterator();
        while(iter.hasNext()){
            Element element=iter.next();
            csslist.add(element.attr("href"));
        }
        return csslist;
    }

    public Integer getStatusCode(){
        return this.response.getStatusLine().getStatusCode();
    }

    public void close(){
        this.httpGet.abort();
        this.httpClient.getConnectionManager().shutdown();
    }


    public boolean isGzip(){
        boolean isgzip=false;
        String gzip=getHeaderMap().get(HttpHeaders.CONTENT_ENCODING);
        if(gzip!=null&&gzip.toLowerCase().contains("gzip")){
            isgzip=true;
        }
        return isgzip;
    }

    public boolean isGzip(String url){
        boolean isgzip=false;
        this.httpGet=new HttpGet(url);
        this.httpGet.addHeader(HttpHeaders.ACCEPT_ENCODING,"gzip,deflate");
        try {
            this.response=httpClient.execute(httpGet);
            String gzip=getHeaderMap().get(HttpHeaders.CONTENT_ENCODING);
            if(gzip!=null&&gzip.toLowerCase().contains("gzip")){
                isgzip=true;
            }
            return isgzip;
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return false;
    }

}

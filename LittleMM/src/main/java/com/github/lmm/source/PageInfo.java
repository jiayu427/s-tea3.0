package com.github.lmm.source;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-6-7
 * Time: 下午4:19
 * To change this template use File | Settings | File Templates.
 */
public class PageInfo {
    private String keyname;
    public PageInfo(){

    }
    public PageInfo(String keyname){
        this.keyname=keyname;
    }

    public String getKeyname() {
        return keyname;
    }

    public void setKeyname(String keyname) {
        this.keyname = keyname;
    }
}

package com.github.lmm.source.xml;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-6-5
 * Time: 上午10:15
 * To change this template use File | Settings | File Templates.
 */
public class BlockElementAttribute {
    private String keyName;
    private String belongTo;

    public BlockElementAttribute(String keyName) {
        this.keyName = keyName;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getBelongTo() {
        return belongTo;
    }

    public void setBelongTo(String belongTo) {
        this.belongTo = belongTo;
    }

}

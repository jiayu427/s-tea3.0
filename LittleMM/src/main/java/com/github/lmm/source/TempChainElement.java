package com.github.lmm.source;

import com.github.lmm.element.TempElement;
import org.openqa.selenium.By;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-6-8
 * Time: 上午9:20
 * To change this template use File | Settings | File Templates.
 */
public class TempChainElement implements TempElement {
    //private PageInfo pageInfo;
    private FrameInfoManager frameInfoManager;
    private ElementInfo elementInfo;
    public TempChainElement(ElementInfo elementInfo){
        this.elementInfo=elementInfo;
        this.frameInfoManager=new FrameInfoManager();
    }

    public TempChainElement(){

    }
    @Override
    public ElementInfo getElementInfo() {
        return this.elementInfo;
    }

    @Override
    public void setElementInfo(ElementInfo elementInfo) {
        this.elementInfo=elementInfo;
    }

    @Override
    public String getId() {
        return this.elementInfo.getId();
    }

    @Override
    public void setId(String id) {
        this.elementInfo.setId(id);
    }

    @Override
    public String getValue() {
        return this.elementInfo.getValue();
    }

    @Override
    public void setValue(String value) {
        this.elementInfo.setValue(value);
    }

    @Override
    public Integer getIndex() {
        return this.elementInfo.getIndex();
    }

    @Override
    public void setIndex(Integer index) {
        this.elementInfo.setIndex(index);
    }

    @Override
    public String getBy() {
        return this.elementInfo.getBy();
    }

    @Override
    public void setBy(String by) {
        this.elementInfo.setBy(by);
    }

    @Override
    public FrameInfoManager getFrameInfoManager() {
        return this.frameInfoManager;
    }

    @Override
    public void setFrameInfoManager(FrameInfoManager frameInfoManager) {
        this.frameInfoManager=frameInfoManager;
    }

    @Override
    public By getLocator() {
        return this.elementInfo.getLocator();
    }

    @Override
    public boolean isFrameElement() {
        if(this.frameInfoManager.getFrameInfoStack().size()>0){
            return true;
        }else{
            return false;
        }
    }
}

package com.github.lmm.element;

import com.github.lmm.source.ElementInfo;
import com.github.lmm.source.FrameInfoManager;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-30
 * Time: 下午1:24
 * To change this template use File | Settings | File Templates.
 */
public interface TempElement {

    public ElementInfo getElementInfo();

    public void setElementInfo(ElementInfo elementInfo);

    public String getId();

    public void setId(String id);

    public String getValue();

    public void setValue(String value);

    public Integer getIndex();

    public void setIndex(Integer index);

    public String getBy();

    public void setBy(String by);

    public By getLocator();

}

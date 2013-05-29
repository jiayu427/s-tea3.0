package com.github.lmm.page;

import com.github.lmm.element.IElement;
import org.openqa.selenium.Cookie;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-29
 * Time: 下午1:44
 * To change this template use File | Settings | File Templates.
 */
public interface TypePage extends IPage {

    public Object[] contains();

    public void addBlock(Object block);


}

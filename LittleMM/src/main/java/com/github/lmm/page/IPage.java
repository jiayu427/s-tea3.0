package com.github.lmm.page;

import org.openqa.selenium.Cookie;
import com.github.lmm.element.IElement;
import org.openqa.selenium.By;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-29
 * Time: 上午10:55
 * To change this template use File | Settings | File Templates.
 */
public interface IPage {

    public Cookie[] getAllCookies();

    void deleteAllCookies();

    public String getTitle();

    public String getUrl();

    public String getCookieByName();

    public IElement currentElement();

    public <T> T load(Class<T> clazz);

    public IElement element(String id);

    public IElement element(By by);

}

package com.github.lmm.page.test;

import com.github.lmm.annotation.Commit;
import com.github.lmm.page.DefaultSourcePage;
import org.openqa.selenium.By;

/**
 * @author 王天庆
 * 测试类
 * */
@Commit("百度首页")
public class BaiduPage extends DefaultSourcePage {
    public void search(){
        //element().addLocator(By.id("kw")).input("北京");
        element().addLocator(By.tagName("form")).childElement(By.id("kw")).input("北京");
        element("百度首页-搜索按钮").click();
    }
}

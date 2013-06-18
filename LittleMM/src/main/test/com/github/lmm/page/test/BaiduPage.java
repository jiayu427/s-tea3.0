package com.github.lmm.page.test;

import com.github.lmm.annotation.Commit;
import com.github.lmm.page.DefaultSourcePage;

/**
 * @author 王天庆
 * 测试类
 * */
@Commit("百度首页")
public class BaiduPage extends DefaultSourcePage {
    public void search(){
        element("百度首页-搜索框").input("北京");
        element("百度首页-搜索按钮").click();
    }
}

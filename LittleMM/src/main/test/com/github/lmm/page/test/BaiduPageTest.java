package com.github.lmm.page.test;

import com.github.lmm.annotation.Browsers;
import com.github.lmm.annotation.Retry;
import com.github.lmm.annotation.ThreadRunner;
import com.github.lmm.browser.Browser;
import com.github.lmm.core.Auto;
import com.github.lmm.runner.JUnitBaseRunner;
import org.databene.benerator.anno.Source;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(JUnitBaseRunner.class)
@ThreadRunner(threads = 1)
@Retry(2)
public class BaiduPageTest {

    @Test
    @Browsers({Browser.PhantomJS})
    @Source("test.xls")
    public void testpage(String text){
        Auto.open("http://www.baidu.com");
        Auto.page(BaiduPage.class).search(text);
        Auto.closeAllWindows();
    }
}

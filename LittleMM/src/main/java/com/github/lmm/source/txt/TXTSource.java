package com.github.lmm.source.txt;

import com.github.lmm.browser.IBrowser;
import com.github.lmm.element.ElementManager;
import com.github.lmm.element.TempElement;
import com.github.lmm.page.IPage;
import com.github.lmm.page.SourcePage;
import com.github.lmm.source.Source;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-29
 * Time: 下午2:27
 * To change this template use File | Settings | File Templates.
 */
public class TXTSource implements Source {

    @Override
    public ElementManager loadSource(IBrowser browser) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Map<String, TempElement> sourceFilter() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Map<String, ElementManager> loadPagesSource() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ElementManager loadPageSource(SourcePage ipage) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


}

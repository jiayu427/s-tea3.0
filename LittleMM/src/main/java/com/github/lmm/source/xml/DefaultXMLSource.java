package com.github.lmm.source.xml;

import com.github.lmm.browser.IBrowser;
import com.github.lmm.element.ElementManager;
import com.github.lmm.element.TempElement;
import com.github.lmm.page.SourcePage;
import org.jsoup.nodes.Document;

import java.io.File;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-6-7
 * Time: 下午1:08
 * To change this template use File | Settings | File Templates.
 */
public class DefaultXMLSource implements XMLSource {

    @Override
    public void initDocument(File file) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void initDocument(String path) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public SingleElement[] getSingleElement(String elementName) {
        return new SingleElement[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public BlockElement[] getBlockElement(String elementName) {
        return new BlockElement[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

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
    public Map<String, TempElement> loadPageSource(SourcePage ipage) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public TempElement getTempElement(String id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ElementManager getElementManager(String commit) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}

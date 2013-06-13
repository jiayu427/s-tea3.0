package com.github.lmm.source.xml;

import com.github.lmm.browser.IBrowser;
import com.github.lmm.core.MyFile;
import com.github.lmm.element.ElementManager;
import com.github.lmm.element.TempElement;
import com.github.lmm.page.SourcePage;

import java.util.HashMap;
import java.util.List;
import com.github.lmm.source.Source;
import java.io.File;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-6-7
 * Time: 下午1:08
 * To change this template use File | Settings | File Templates.
 */
public class DefaultXMLSource implements Source {
    private List<String> filelist;
    public DefaultXMLSource(){
        MyFile myFile=new MyFile();
        filelist=myFile.listFile(new File("source"),"xml",true);
    }


    @Override
    public ElementManager loadSource(IBrowser browser) {
        ElementManager manager = new ElementManager();
        for(String path:filelist){
            Dom4jTools dom4jTools=new Dom4jTools(path);
            manager.addElements(dom4jTools.getPageElementManager().get("browser"));
        }
        return manager;
    }

    @Override
    public Map<String, TempElement> sourceFilter() {
        return null;
    }

    @Override
    public Map<String, ElementManager> loadPagesSource() {
        Map<String,ElementManager> manager = new HashMap<String,ElementManager>();
        for(String path:filelist){
            Dom4jTools dom4jTools=new Dom4jTools(path);
            Map<String,ElementManager> filemap=dom4jTools.getPageElementManager();
            filemap.remove("browser");
            manager.putAll(filemap);
        }
        return manager;
    }

    @Override
    public ElementManager loadPageSource(SourcePage ipage) {
        return loadPagesSource().get(ipage.getPageCommit());
    }
}

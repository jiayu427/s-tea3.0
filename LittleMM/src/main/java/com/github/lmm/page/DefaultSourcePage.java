package com.github.lmm.page;

import com.github.lmm.browser.IBrowser;
import com.github.lmm.core.Auto;
import com.github.lmm.source.xml.DefaultXMLSource;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-6-8
 * Time: 上午10:58
 * To change this template use File | Settings | File Templates.
 */
public class DefaultSourcePage extends SourcePage{

    public DefaultSourcePage(String commit){
        super(Auto.browser(),commit,new DefaultXMLSource());
    }
}

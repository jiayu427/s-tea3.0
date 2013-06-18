package com.github.lmm.page;

import com.github.lmm.annotation.Commit;
import com.github.lmm.core.Auto;
import com.github.lmm.source.xml.DefaultXMLSource;

/**
 * @author 王天庆
 * 这个类是默认的page类，我们编写的所有的page类都需要继承这个类
 * */
public class DefaultSourcePage extends SourcePage{

    public DefaultSourcePage(){
        super(Auto.browser(),new DefaultXMLSource());
        this.setPageCommit(this.getClass().getAnnotation(Commit.class).value());
    }
}

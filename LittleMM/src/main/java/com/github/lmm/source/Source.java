package com.github.lmm.source;

import com.github.lmm.browser.IBrowser;
import com.github.lmm.element.ElementManager;
import com.github.lmm.element.TempElement;
import com.github.lmm.page.IPage;
import com.github.lmm.page.SourcePage;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-29
 * Time: 下午1:39
 * To change this template use File | Settings | File Templates.
 */
public interface Source {
    /**这个方法的意思是加载资源，得到所有的加载资源，这些资源并没有做任何的分类处理，对于小量数据的时候
     * 或者页面复杂度比较小的时候比较好管理，但是在page模式的时候一个页面就全部加载会有很大的性能浪费。
     * */
    public ElementManager loadSource(IBrowser browser);
    /**此方法提供了一个过滤器，过滤掉一些不想要的资源*/
    public Map<String,TempElement> sourceFilter();
    /**此方法是loadSource的补充，如果我们加载的时候只需要加载一个固定的页面的资源，完全不需要加载全部的资源
     * 定义加载规则，就可以通过指定的id或者页面的commit来加载存在里面的资源，分批加载，指定加载。节约资源，
     * 提供性能。
     * */
    public Map<String,ElementManager> loadPagesSource();


    /**此方法和loadSource()不一样的是，这个方法提供了page类的参数化，这样通过直接加载page类就可以解决资源问题，page类会
     * 内置一个资源的构造方法，这样的话这个资源本身就存在，如果存在，自动的加载，如果不存在就不再加载。
     * page类将会提供了一个默认的加载的方法，这个方法默认的存在，我们可以通过自定义的方式更改元素的加载。
     * */
    public ElementManager loadPageSource(SourcePage ipage);


}

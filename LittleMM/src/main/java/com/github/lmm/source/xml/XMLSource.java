package com.github.lmm.source.xml;

import java.io.File;
import com.github.lmm.source.Source;

/**
 * Created with IntelliJ IDEA.
 * User: ouamaqing
 * Date: 13-5-29
 * Time: 下午2:26
 * To change this template use File | Settings | File Templates.
 */
public interface XMLSource extends Source {
    /**这个方法初始化获取的资源目录或者文件，能够加载所有的文件内的资源*/
    public void initDocument(File file);
    /**这个方法初始化获取的资源目录或者文件，能够加载所有的文件内的资源*/
    public void initDocument(String path);
    /**这个方法定义了一个获取xml单个元素的方法，通过元素名称获取*/


}

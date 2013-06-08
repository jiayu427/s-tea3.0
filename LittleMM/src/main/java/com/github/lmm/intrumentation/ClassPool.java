package com.github.lmm.intrumentation;

import java.util.HashSet;
import java.util.Set;

import com.github.lmm.core.ConfigParser;



/**
 * 本地class文件的扫描方法，初始化只能扫描一次，不能够重复扫描，如果需要重置
 * 
 * 
 * */
public class ClassPool {
	
	static private Set<Class<?>> classPool=new HashSet<Class<?>>();
	
	/**
	 *@return 返回项目目录下的class的集合。
	 */
	public static Set<Class<?>> getClassPool(){
		if(classPool.size()==0){
			ClassPathScanHandler cpsh = new ClassPathScanHandler(false, true, null);
			classPool=cpsh.getPackageAllClasses(ConfigParser.getBaseName(), true);
			return classPool;
		}else{
			return classPool;
		}
		
	}
	/**
	 *@param clazz 需要添加的class对象，需要在classpath找到的class
	 */
	public static void addClass(Class<?> clazz){
		classPool.add(clazz);
	}
	
	
	public static void reset(){
		classPool.clear();
	}
	
	
	public static void removeClass(Class<?> clazz){
		classPool.remove(clazz);
	}
}

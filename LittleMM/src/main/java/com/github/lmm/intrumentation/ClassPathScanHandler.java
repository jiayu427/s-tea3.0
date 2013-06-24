package com.github.lmm.intrumentation;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Pattern;

import com.github.lmm.runtime.RuntimeMethod;
import org.apache.log4j.Logger;


public class ClassPathScanHandler {
	
	private static Logger logger = Logger.getLogger(ClassPathScanHandler.class);
	/**
	 * 定义扫描器是否扫描带有内部类的class，默认的不扫描
	 * */
	private boolean excludeInner = true;
	
	/**
     *该参数表示过滤规则适用情况，true—>搜索符合规则的 false->排除符合规则的，默认=true
     */
    private boolean checkInOrEx = true;

	 /**
	  * 自定义过滤规则，如果是null或者空，即全部符合不过滤，默认为null。过滤规则可以自定义为：<br>
	  * Xyz或Xyz*或*Xyz或*Xyz*等类似的格式
	  * */
	private List<String> classFilters = null;
	
	public ClassPathScanHandler(){
		
	}
	
	public ClassPathScanHandler(boolean excludeInner,boolean checkInOrEx,List<String> classFilters){
		this.excludeInner=excludeInner;
		this.checkInOrEx=checkInOrEx;
		this.classFilters=classFilters;
	}
	
	 /**
     * 
     * @param basePackage 基础包
     * @param recursive 是否递归搜索子包
     * @return Set
     */
	public Set<Class<?>>getPackageAllClasses(String basePackage,boolean recursive){
		Set<Class<?>> classes=new HashSet<Class<?>>();
		String baseName=basePackage;
		if(baseName.endsWith(".")){
			baseName=baseName.substring(0, basePackage.lastIndexOf("."));
		}
		String package2path=baseName.replace(".", "/");
		Enumeration<URL> dirs;
		try{
			dirs=Thread.currentThread().getContextClassLoader().getResources(package2path);
			while(dirs.hasMoreElements()){
				URL url = dirs.nextElement();
				String protocol = url.getProtocol();
				if("file".equals(protocol)){
					String filePath = URLDecoder.decode(url.getFile(),"UTF-8");
					logger.info("["+ com.github.lmm.runtime.RuntimeMethod.getName()+"]"+"扫描file类型的class文件...");
					doScanPackageClassesByFile(classes, baseName, filePath,recursive);
				}else if("jar".equals(protocol)){
					logger.info("["+ RuntimeMethod.getName()+"]"+"扫描jar文件中的类...");
					doScanPackageClassesByJAR(baseName, url, recursive, classes);
				}
			}
		}catch(IOException e){
			logger.error("IOException error:", e);
		}
		return classes;		
	}
	
	/**
    * 以jar的方式扫描包下的所有Class文件<br>
    * @param baseName eg:org.sky.
    * @param url
    *      * @param recursive
    * @param classes
    */
	private void doScanPackageClassesByJAR(String baseName, URL url,
			boolean recursive, Set<Class<?>> classes) {
		String packageName=baseName;
		String package2Path = packageName.replace(".", "/");
		JarFile jar;
		try{
			jar=((JarURLConnection)url.openConnection()).getJarFile();
			Enumeration<JarEntry> enties = jar.entries();
			while(enties.hasMoreElements()){
				JarEntry je = enties.nextElement();
				String name =je.getName();
				if(!name.startsWith(package2Path)||je.isDirectory()){
					continue;
				}
				if(!recursive&&name.lastIndexOf("/")!=package2Path.length()){
					continue;
				}
				String classSimpleName = name.substring(name.lastIndexOf('/') + 1);
				if(this.fiterClassName(classSimpleName)){
					String className = name.replace("/", ".");
					className = className.substring(0, className.length()-6);
					try{
						classes.add(Thread.currentThread().getContextClassLoader().loadClass(className));
						logger.info("className:"+className);
					}catch(ClassNotFoundException e){
						logger.error("Class.forName Error:"+e);
					}
				}

			}
		}catch(IOException e){
			logger.error("IOException error:"+e);
		}
		
	}
	

	private boolean fiterClassName(String classSimpleName) {
		// TODO Auto-generated method stub
		return false;
	}

	/** 以文件的方式扫描包下的所有Class文件
	 * @param classes
	 * @param baseName
	 * @param filePath
	 * @param recursive 
	 * */
	private void doScanPackageClassesByFile(Set<Class<?>> classes,
			String baseName, String filePath, boolean recursive) {
		File dir = new File(filePath);
		if(!dir.exists()||!dir.isDirectory()){
			return;
		}
		final boolean fileRecursive=recursive;
		File[] dirlist = dir.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File file) {
				  if (file.isDirectory()) {
	                    return fileRecursive;
	                }
	                String filename = file.getName();
	                if (excludeInner && filename.indexOf('$') != -1) {
	                  //  logger.info("exclude inner class with name:" + filename);
	                    return false;
	                }
	                return filterClassName(filename);
			}
		});
		for(File file:dirlist){
			if(file.isDirectory()){
				doScanPackageClassesByFile(classes, baseName+"."+file.getName(),file.getAbsolutePath(), recursive);
			}else{
				String className = file.getName().substring(0, file.getName().length()-6);
				try{
					classes.add(Thread.currentThread().getContextClassLoader().loadClass(baseName+"."+className));
				}catch(ClassNotFoundException e){
					logger.error("Class.forName ERROR:"+e);
				}
			}
		}
		
		
	}

	  /**

     * 根据过滤规则判断类名

     * @param className

     * @return

     */

    private boolean filterClassName(String className) {
        if (!className.endsWith(".class")) {
            return false;
        }
        if (null == this.classFilters || this.classFilters.isEmpty()) {
            return true;
        }
        String tmpName = className.substring(0, className.length() - 6);
        boolean flag = false;
        for (String str : classFilters) {
            String tmpreg = "^" + str.replace("*", ".*") + "$";
            Pattern p = Pattern.compile(tmpreg);
            if (p.matcher(tmpName).find()) {
                flag = true;
                break;
            }
        }
        return (checkInOrEx && flag) || (!checkInOrEx && !flag);
    }

	public boolean isExcludeInner() {
		return excludeInner;
	}

	public void setExcludeInner(boolean excludeInner) {
		this.excludeInner = excludeInner;
	}

	public boolean isCheckInOrEx() {
		return checkInOrEx;
	}

	public void setCheckInOrEx(boolean checkInOrEx) {
		this.checkInOrEx = checkInOrEx;
	}

	public List<String> getClassFilters() {
		return classFilters;
	}

	public void setClassFilters(List<String> classFilters) {
		this.classFilters = classFilters;
	}

	
	
}

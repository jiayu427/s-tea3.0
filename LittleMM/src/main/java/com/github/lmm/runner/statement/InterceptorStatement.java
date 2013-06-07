package com.github.lmm.runner.statement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//import org.apache.log4j.Logger;
import com.github.lmm.annotation.Browsers;
import com.github.lmm.annotation.SingleBrowser;
import com.github.lmm.browser.Browser;
import com.github.lmm.core.Auto;
import com.github.lmm.runtime.RuntimeMethod;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

public class InterceptorStatement extends Statement{
	//private static Logger logger = Logger.getLogger(InterceptorStatement.class);
	private final FrameworkMethod testMethod;
    private Object target;
	public InterceptorStatement(FrameworkMethod testMethod, Object target) {
		this.testMethod=testMethod;
		this.target=target;
	}
	private List<Interceptor> interceptors = new ArrayList<Interceptor>();

	@Override
	public void evaluate() throws Throwable {
		addInterceptor(new ThreadLocalResetInterceptor());
		for(Interceptor interceptor:interceptors){
			interceptor.interceptorBefore();
		}
            String className=testMethod.getMethod().getClass().getName();
            String name="Case:"+className.substring(className.lastIndexOf(".")+1, className.length())+"=>"+testMethod.getName();
            RuntimeMethod.setName(name);
        if(testMethod.getMethod().isAnnotationPresent(Browsers.class)){
            Browsers browsers=testMethod.getMethod().getAnnotation(Browsers.class);
            Browser[] bs=browsers.value();
            Auto.require(bs);
            if(Auto.browserSet.get().size()!=0){
                System.out.println(Auto.browserSet.get().size());
                Iterator<Browser> iterator = Auto.browserSet.get().iterator();
                while(iterator.hasNext()){
                    Browser browser=iterator.next();
                    Auto.require(browser);
                    testMethod.invokeExplosively(target);
                }
                Auto.local.get().setBrowser(null);
            }else{
                throw new RuntimeException("["+RuntimeMethod.getName()+"]注解中给定的浏览器数据不正确！");
            }
        }else if(testMethod.getMethod().isAnnotationPresent(SingleBrowser.class)){
            SingleBrowser b = testMethod.getMethod().getAnnotation(SingleBrowser.class);
            Browser browser=b.value();
            Auto.require(browser);
            testMethod.invokeExplosively(target);
        }else{
            testMethod.invokeExplosively(target);
        }
        RuntimeMethod.setName(null);
		for(Interceptor interceptor:interceptors){
			interceptor.interceptorAfter();
		}

	}
	
	
	public void addInterceptor(Interceptor interceptor){
	}
}

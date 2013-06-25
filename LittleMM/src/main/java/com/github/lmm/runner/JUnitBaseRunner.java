package com.github.lmm.runner;


import com.github.lmm.intrumentation.ClassPool;
import com.github.lmm.proxy.ActionListenerProxy;
import com.github.lmm.proxy.RunnerListenerProxy;
import org.apache.log4j.PropertyConfigurator;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;
import org.apache.log4j.Logger;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.RunnerScheduler;
import org.junit.runners.model.Statement;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import com.github.lmm.annotation.*;
import com.github.lmm.runner.statement.*;
/**
 * @author 王天庆
 * */
public class JUnitBaseRunner extends Feeder{
    private Logger logger = Logger.getLogger(JUnitBaseRunner.class);
    public JUnitBaseRunner(final Class<?> klass)
            throws InitializationError {
        super(klass);
        PropertyConfigurator.configure("resource/log4j.properties");
        setScheduler(new RunnerScheduler() {
            ExecutorService executorService = Executors.newFixedThreadPool(
                    klass.isAnnotationPresent(ThreadRunner.class) ?
                            klass.getAnnotation(ThreadRunner.class).threads() :
                            (int) (Runtime.getRuntime().availableProcessors() * 1.5),
                    new NamedThreadFactory(klass.getSimpleName()));
            CompletionService<Void> completionService = new ExecutorCompletionService<Void>(executorService);
            Queue<Future<Void>> tasks = new LinkedList<Future<Void>>();

            public void schedule(Runnable childStatement) {
                tasks.offer(completionService.submit(childStatement, null));
            }


            public void finished() {
                try {
                    while (!tasks.isEmpty())
                        tasks.remove(completionService.take());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    while (!tasks.isEmpty())
                        tasks.poll().cancel(true);
                    executorService.shutdownNow();
                }
            }

        });
    }
    static final class NamedThreadFactory implements ThreadFactory {
        static final AtomicInteger poolNumber = new AtomicInteger(1);
        final AtomicInteger threadNumber = new AtomicInteger(1);
        final ThreadGroup group;

        NamedThreadFactory(String poolName) {
            group = new ThreadGroup(poolName + "-" + poolNumber.getAndIncrement());
        }


        public Thread newThread(Runnable r) {
            return new Thread(group, r, group.getName() + "-thread-" + threadNumber.getAndIncrement(), 0);
        }
    }


    protected Statement methodInvoker(FrameworkMethod method, Object test) {
        InterceptorStatement statement = new InterceptorStatement(method, test);
        if(test.getClass().isAnnotationPresent(InterceptorClass.class)){
            InterceptorClass anno = test.getClass().getAnnotation(InterceptorClass.class);
            Class<?>[] clazzs = anno.value();
            try{
                for(Class<?> clazz : clazzs){
                    statement.addInterceptor((Interceptor)clazz.newInstance());
                }
            }catch(IllegalAccessException ilex){
                ilex.printStackTrace();
            }catch(InstantiationException e){
                e.printStackTrace();
            }
        }
        return statement;
    }

    public void run(RunNotifier runNotifier){
        Set<Class<?>> cls = ClassPool.getClassPool();
        //PageManager.collectPageInfomation();
        for(Class<?>clazz : cls){
            if(clazz.isAnnotationPresent(Register.class)){
                try {
                    ActionListenerProxy.register(clazz);
                    logger.info("扫描到了动作级别的监听器"+clazz.getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(clazz.isAnnotationPresent(RunnerListener.class)){
                try {
                    RunnerListenerProxy.register((RunListener)clazz.newInstance());
                } catch (InstantiationException e) {
                    e.printStackTrace();
                    throw new RuntimeException("在加载运行级别监听器的时候出现了错误！");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    throw new RuntimeException("在加载运行级别监听器的时候出现了错误！");
                }
            }
        }
        if(RunnerListenerProxy.dispatcher().size()!=0){
            for(RunListener rl:RunnerListenerProxy.dispatcher()){
                runNotifier.addListener(rl);
            }
        }
        super.run(runNotifier);
    }

}

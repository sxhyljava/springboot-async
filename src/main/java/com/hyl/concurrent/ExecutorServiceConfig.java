package com.hyl.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author
 * @Description
 * @date 2020-03-02 14:16
 */
@Slf4j
@Configuration
public class ExecutorServiceConfig {

    /**
     * 使用方法
     * 在需要使用线程的函数或者类上添加
     * @Async("taskServiceExecutor")
     * 注意不要在和调用函数的类直接注解
     * 要用单独的类
     * @return
     */


    @Bean(name = "taskServiceExecutor")
    public Executor asyncServiceExecutor(){
        log.info("start asyncServiceExecutor");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(10);
        //配置最大线程数
        executor.setMaxPoolSize(100);
        //配置队列大小
        executor.setQueueCapacity(99999);

        executor.setKeepAliveSeconds(200);
        executor.setThreadNamePrefix("task-async-service-");
        //当pool已经达到max的时候，如何处理新任务
        //caller_runs :不在新线程中执行任务，而是调用者所在的线程来执行
        // 线程池对拒绝任务（无线程可用）的处理策略，目前只支持AbortPolicy、CallerRunsPolicy；默认为后者
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //调度器shutdown被调用时等待当前被调度的任务完成
        executor.setWaitForTasksToCompleteOnShutdown(true);
        //等待时长
        executor.setAwaitTerminationSeconds(60);
        executor.initialize();
        return executor;
    }
}

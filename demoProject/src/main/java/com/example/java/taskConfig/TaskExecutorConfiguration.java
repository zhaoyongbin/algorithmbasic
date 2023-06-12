package com.example.java.taskConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

public class TaskExecutorConfiguration {
    @Value("${sfsdf}")
    private int corePoolSize;
    @Value("${sfsdf}")
    private int maximumPoolSize;

    @Bean("taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(20);
        executor.setMaxPoolSize(40);
        executor.setQueueCapacity(60);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("bip-test-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

    @Bean("executorService")
    public ExecutorService executorService() {
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 0L, TimeUnit.SECONDS,
                        new LinkedBlockingDeque<Runnable>(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());

        return threadPoolExecutor;

    }

}

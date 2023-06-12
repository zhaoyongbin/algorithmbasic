package com.example.java.asyncAspect.config;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskExecutor {
    private int coreSize;
    private int maxSize;
    private String prefix;
    private ThreadPoolExecutor pool = null;

    public TaskExecutor(int coreSize, int maxSize, String prefix) {
        this.coreSize = coreSize;
        this.maxSize = maxSize;
        this.prefix = prefix;
        this.pool = new ThreadPoolExecutor(coreSize, maxSize, 60L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
    }

    public void execute(Runnable task) {
        this.pool.execute(task);
    }

    class AuditLogRecordThreadFactory implements ThreadFactory {

        private AtomicInteger theadIdx = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            String name = this.resolveThreadName();
            return new Thread(r, name);
        }

        private String resolveThreadName() {
            int maxIdx = Math.max(TaskExecutor.this.coreSize, TaskExecutor.this.maxSize);
            int curIdx = this.theadIdx.getAndIncrement();
            if (curIdx == maxIdx) {
                curIdx = 0;
                this.theadIdx.set(0);
            }
            return TaskExecutor.this.prefix + curIdx;
        }
    }
}

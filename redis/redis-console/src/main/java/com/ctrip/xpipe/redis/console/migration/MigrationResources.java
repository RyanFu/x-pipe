package com.ctrip.xpipe.redis.console.migration;

import com.ctrip.xpipe.spring.AbstractSpringConfigContext;
import com.ctrip.xpipe.utils.XpipeThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author wenchao.meng
 *         <p>
 *         Aug 01, 2017
 */
@Configuration
public class MigrationResources {

    public static final String MIGRATION_EXECUTOR = "MIGRATION_EXECUTOR";

    public static final int maxThreads = 512;

    @Bean(name = MIGRATION_EXECUTOR)
    public ExecutorService getMigrationlExecutor() {

        return new ThreadPoolExecutor(4,
                maxThreads,
                120L, TimeUnit.SECONDS,
                new SynchronousQueue<>(),
                XpipeThreadFactory.create(MIGRATION_EXECUTOR),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    @PreDestroy
    public void shutdown() {
        ExecutorService executor = (ExecutorService)AbstractSpringConfigContext
                .applicationContext.getBean(MIGRATION_EXECUTOR);
        executor.shutdownNow();
    }
}
package com.kanni.jobrunrDemo.config;

import org.jobrunr.configuration.JobRunr;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.storage.InMemoryStorageProvider;
import org.jobrunr.storage.StorageProvider;
import org.jobrunr.storage.sql.mysql.MySqlStorageProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


@Configuration
public class JobRunrManualConfig {

//    @Bean
//    public StorageProvider storageProvider() {
//        return new InMemoryStorageProvider();
//    }

    @Bean
    public StorageProvider storageProvider(DataSource dataSource) {
        return new MySqlStorageProvider(dataSource);
    }

    @Bean
    public JobScheduler jobScheduler(StorageProvider storageProvider) {
        return JobRunr.configure()
                .useStorageProvider(storageProvider)
                .useBackgroundJobServer() // Optional
                .initialize()
                .getJobScheduler();
    }
}

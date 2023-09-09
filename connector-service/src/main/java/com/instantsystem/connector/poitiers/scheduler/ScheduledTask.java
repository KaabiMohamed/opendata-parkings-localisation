package com.instantsystem.connector.poitiers.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Poitiers Job Scheduler
 * This class schedules and runs batch jobs for parking data.
 */
@Service
public class ScheduledTask {
    Logger LOG = LoggerFactory.getLogger(ScheduledTask.class);

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job parkingDataJob;

    @Autowired
    private Job parkingRealTimDataJob;

    /**
     * Scheduled method to execute the parkingDataJob batch job.
     * This method runs at fixed intervals as defined by the cron expression.
     *
     * @throws Exception if an error occurs during job execution.
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void executeParkingFullDataJob() throws Exception {
        // Create job parameters with a unique identifier
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("parkingDataJob", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();

        // Launch the parkingDataJob
        jobLauncher.run(parkingDataJob, jobParameters);
    }

    /**
     * Scheduled method to execute the parkingRealTimDataJob batch job.
     * This method runs at fixed intervals as defined by the cron expression.
     *
     * @throws Exception if an error occurs during job execution.
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void executeParkingRealTimDataJob() throws Exception {
        // Create job parameters with a unique identifier
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("parkingRealTimDataJob", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();

        // Launch the parkingRealTimDataJob
        jobLauncher.run(parkingRealTimDataJob, jobParameters);
    }
}

package com.instantsystem.connector.poitiers.batch;

import com.instantsystem.connector.poitiers.mapper.CustomMapper;
import com.instantsystem.connector.poitiers.model.ParkingRealTimeData;
import com.instantsystem.connector.global.repository.ParkingRepository;
import com.instantsystem.connector.poitiers.service.ParkingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * ParkingRealTimeDataBatchConfiguration: Configuration for the batch job processing real-time parking data.
 */
@Configuration
@EnableBatchProcessing
public class ParkingRealTimeDataBatchConfiguration {

    Logger LOG = LoggerFactory.getLogger(ParkingRealTimeDataBatchConfiguration.class);

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    ParkingService parkingService;

    @Autowired
    ParkingRepository parkingRepository;

    @Autowired
    CustomMapper mapper;

    /**
     * Define a batch step for processing real-time parking data.
     *
     * @return A configured Step.
     */
    @Bean("stepParkingRealTimData")
    public Step stepParkingRealTimData() {
        return stepBuilderFactory
                .get("stepParkingRealTimData")
                .tasklet((contribution, chunkContext) -> {
                    List<ParkingRealTimeData> parkings;
                    int page = 0;
                    do {
                        // Fetch a page of real-time parking data
                        parkings = parkingService.getParkingsRealTimeDataList(page);
                        parkings.forEach(park -> {
                            if (park.geoPoint2d != null) {
                                // Update the parking entity with real-time data
                                parkingRepository.updateParkingEntityWithRealTimeData(mapper.mapReaTimeDataInParkingEntity(park));
                            } else {
                                LOG.info(String.format("Could not save entry due to GPS point missing: %s", park.toString()));
                            }
                        });
                        page++;
                    } while (!parkings.isEmpty());

                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    /**
     * Define a job for processing real-time parking data, with the specified step.
     *
     * @param step The step to execute.
     * @return A configured Job.
     */
    @Bean
    public Job parkingRealTimDataJob(@Qualifier("stepParkingRealTimData") Step step) {
        return jobBuilderFactory
                .get("parkingRealTimDataJob")
                .start(step)
                .build();
    }
}

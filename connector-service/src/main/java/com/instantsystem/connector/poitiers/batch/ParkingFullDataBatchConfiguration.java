package com.instantsystem.connector.poitiers.batch;

import com.instantsystem.connector.poitiers.mapper.CustomMapper;
import com.instantsystem.connector.poitiers.model.Parking;
import com.instantsystem.connector.global.repository.ParkingRepository;
import com.instantsystem.connector.poitiers.service.ParkingService;
import org.locationtech.jts.io.ParseException;
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
 * ParkingFullDataBatchConfiguration: Configuration for the batch job processing full parking data.
 */
@Configuration
@EnableBatchProcessing
public class ParkingFullDataBatchConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(ParkingFullDataBatchConfiguration.class);

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
     * Define a batch step for processing full parking data.
     *
     * @return A configured Step.
     */
    @Bean
    public Step stepParkingData() {
        return stepBuilderFactory
                .get("stepParkingData")
                .tasklet((contribution, chunkContext) -> {
                    List<Parking> parkings;
                    int page = 0;
                    do {
                        // Fetch a page of parking data
                        parkings = parkingService.getParkingsDataList(page);
                        parkings.forEach(park -> {
                            if (park.geoPoint2d != null) {
                                try {
                                    // Insert the parking data into the repository
                                    parkingRepository.insertParking(mapper.map(park));
                                } catch (ParseException e) {
                                    throw new RuntimeException(e);
                                }
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
     * Define a job for processing full parking data, with the specified step.
     *
     * @param step The step to execute.
     * @return A configured Job.
     */
    @Bean
    public Job parkingDataJob(@Qualifier("stepParkingData") Step step) {
        return jobBuilderFactory
                .get("parkingDataJob")
                .start(step)
                .build();
    }
}

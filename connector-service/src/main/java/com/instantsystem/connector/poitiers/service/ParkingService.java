package com.instantsystem.connector.poitiers.service;

import com.instantsystem.connector.poitiers.datasource.DataSourceClient;
import com.instantsystem.connector.poitiers.mapper.CustomMapper;
import com.instantsystem.connector.poitiers.model.Parking;
import com.instantsystem.connector.poitiers.model.ParkingDataResult;
import com.instantsystem.connector.poitiers.model.ParkingRealTimeData;
import com.instantsystem.connector.poitiers.model.ParkingRealTimeDataResult;
import com.instantsystem.connector.global.repository.ParkingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * ParkingService: Service class for handling parking data.
 */
@Service
@Transactional
public class ParkingService {

    private static final Logger LOG = LoggerFactory.getLogger(ParkingService.class);

    // Configuration values from application.properties
    @Value("${datasource.poitiers.full.data.fields.select}")
    private String selectedFieldFromParkingData;

    @Value("${datasource.poitiers.real.time.data.fields.select}")
    private String selectedFieldFromParkingRealTimeData;

    @Value("${datasource.poitiers.full.data.limit.select}")
    private int parkingDataSelectLimit;

    @Value("${datasource.poitiers.real.time.limit.select}")
    private int parkingRealTimeDataSelectLimit;

    @Autowired
    DataSourceClient dataSourceClient;

    @Autowired
    ParkingRepository parkingRepository;

    @Autowired
    CustomMapper mapper;

    /**
     * Get a list of parking data based on the specified page.
     *
     * @param page The page number.
     * @return A list of parking data.
     */
    public List<Parking> getParkingsDataList(int page) {
        ParkingDataResult result = dataSourceClient.fetchAllParkingData(selectedFieldFromParkingData, parkingDataSelectLimit, calculateOffset(parkingDataSelectLimit, page));
        return result.getParkings();
    }

    /**
     * Get a list of real-time parking data based on the specified page.
     *
     * @param page The page number.
     * @return A list of real-time parking data.
     */
    public List<ParkingRealTimeData> getParkingsRealTimeDataList(int page) {
        ParkingRealTimeDataResult result = dataSourceClient.fetchAllRealTimePlacesData(selectedFieldFromParkingRealTimeData, parkingRealTimeDataSelectLimit, calculateOffset(parkingRealTimeDataSelectLimit, page));
        return result.getRealTimeData();
    }

    /**
     * Calculate the offset based on the page and data size.
     *
     * @param size The size of data.
     * @param page The page number.
     * @return The calculated offset.
     */
    private int calculateOffset(int size, int page) {
        return size * page;
    }
}

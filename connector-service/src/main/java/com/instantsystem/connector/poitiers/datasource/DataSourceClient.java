package com.instantsystem.connector.poitiers.datasource;

import com.instantsystem.connector.poitiers.model.ParkingDataResult;
import com.instantsystem.connector.poitiers.model.ParkingRealTimeDataResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * DataSourceClient: Feign client for interacting with the Poitiers data source.
 */
@FeignClient(name = "grandpoitiers", url = "${datasource.poitiers.url}")
public interface DataSourceClient {

    /**
     * Fetch parking data from the Poitiers data source.
     *
     * @param select The fields to select.
     * @param limit  The limit for the number of records to fetch.
     * @param offset The offset for pagination.
     * @return A ParkingDataResult containing parking data.
     */
    @GetMapping("/mobilite-parkings-grand-poitiers-donnees-metiers/records")
    ParkingDataResult fetchAllParkingData(
            @RequestParam("select") String select,
            @RequestParam("limit") int limit,
            @RequestParam("offset") int offset
    );

    /**
     * Fetch real-time parking data from the Poitiers data source.
     *
     * @param select The fields to select.
     * @param limit  The limit for the number of records to fetch.
     * @param offset The offset for pagination.
     * @return A ParkingRealTimeDataResult containing real-time parking data.
     */
    @GetMapping("/mobilites-stationnement-des-parkings-en-temps-reel/records")
    ParkingRealTimeDataResult fetchAllRealTimePlacesData(
            @RequestParam("select") String select,
            @RequestParam("limit") int limit,
            @RequestParam("offset") int offset
    );
}

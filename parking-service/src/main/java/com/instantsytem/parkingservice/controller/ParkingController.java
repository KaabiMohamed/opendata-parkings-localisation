package com.instantsytem.parkingservice.controller;


import com.instantsytem.parkingservice.dto.ParkingResponse;
import com.instantsytem.parkingservice.facade.ParkingFacade;
import org.locationtech.jts.io.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * This class is the Parking Controller
 */
@RestController
@RequestMapping("/v1")
public class ParkingController {

    private static final Logger LOG = LoggerFactory.getLogger(ParkingController.class);

    @Autowired
    public
    ParkingFacade parkingFacade;

    /**
     * Retrieve all parking's on a radius starting from a point with a count on results
     *
     * @param longitude x position
     * @param latitude y position
     * @param radius radius of search
     * @return List of parking with distance between target point and returned parking calculation ordered by distance
     * @throws ParseException when encountering a problem while creating Point object
     */

    @GetMapping("/parking-lots")
    public ParkingResponse getParking(
            @Valid @RequestParam(value = "longitude") Double longitude,
            @Valid @RequestParam(value = "latitude") Double latitude,
            @Valid @RequestParam(value = "radius") Integer radius
    ) throws ParseException {
        if(LOG.isDebugEnabled()){
            LOG.debug(String.format("Request : {longitude: %s, latitude: %s, radius : %s }",longitude,latitude,radius));
        }
        return parkingFacade.findParkingListWithinRadius(longitude,latitude,radius);
    }

}

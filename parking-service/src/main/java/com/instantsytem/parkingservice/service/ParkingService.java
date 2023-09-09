package com.instantsytem.parkingservice.service;

import com.instantsytem.parkingservice.controller.ParkingController;
import com.instantsytem.parkingservice.entity.ParkingEntity;
import com.instantsytem.parkingservice.repository.ParkingRepository;
import org.locationtech.jts.geom.CoordinateXY;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.locationtech.jts.io.WKTWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class is the parking service
 */
@Service
public class ParkingService {

    private static final Logger LOG = LoggerFactory.getLogger(ParkingService.class);


    @Autowired
    ParkingRepository parkingRepository;

    /**
     * Retrieve all parking's on a radius starting from a point
     *
     * @param longitude x position
     * @param latitude y position
     * @param radius radius of search
     * @return List of parking with distance between target point and returned parking calculation ordered by distance
     * @throws ParseException when encountering a problem while creating Point object
     */
    public List<ParkingEntity> findParkingListWithinRadius(double longitude,double latitude,int radius) throws ParseException {
        Point point = (Point) new WKTReader().read(WKTWriter.toPoint(new CoordinateXY(longitude,latitude)));
        if(LOG.isDebugEnabled()){
            LOG.debug(String.format("Reading from ParkingEntity with params {%s,%s}",point.toString(),radius));
        }
        return parkingRepository.findParkingWithinRadiusNativeQuery(point,radius);
    }

}

package com.instantsytem.parkingservice.facade;

import com.instantsytem.parkingservice.dto.ParkingDto;
import com.instantsytem.parkingservice.dto.ParkingResponse;
import com.instantsytem.parkingservice.entity.ParkingEntity;
import com.instantsytem.parkingservice.mapper.ParkingMapper;
import com.instantsytem.parkingservice.service.ParkingService;
import org.locationtech.jts.io.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class is the Parking Facade
 */
@Service
public class ParkingFacade {

    @Autowired
    ParkingService parkingService;

    @Autowired
    ParkingMapper parkingMapper;

    /**
     * Retrieve all parking's on a radius starting from a point with a count on results
     *
     * @param longitude x position
     * @param latitude y position
     * @param radius radius of search
     * @return ParkingResponse Contains List of parking and totalItems
     * @throws ParseException From parkingService
     */
    public ParkingResponse findParkingListWithinRadius(double longitude,double latitude,int radius) throws ParseException {
        List<ParkingEntity> entityList = parkingService.findParkingListWithinRadius(longitude,latitude,radius);
        List<ParkingDto> dtoList = parkingMapper.mapEntitiesToDTOs(entityList);
        return new ParkingResponse().result(dtoList).totalItems(dtoList.size());
    }

}

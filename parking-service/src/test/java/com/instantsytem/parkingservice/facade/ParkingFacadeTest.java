package com.instantsytem.parkingservice.facade;

import com.instantsytem.parkingservice.dto.ParkingDto;
import com.instantsytem.parkingservice.dto.ParkingResponse;
import com.instantsytem.parkingservice.entity.ParkingEntity;
import com.instantsytem.parkingservice.mapper.ParkingMapper;
import com.instantsytem.parkingservice.service.ParkingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.io.ParseException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class ParkingFacadeTest {

    private ParkingFacade parkingFacade;

    @BeforeEach
    public void setUp() {
        ParkingService parkingService = mock(ParkingService.class);
        ParkingMapper parkingMapper = mock(ParkingMapper.class);
        parkingFacade = new ParkingFacade();
        parkingFacade.parkingService = parkingService;
        parkingFacade.parkingMapper = parkingMapper;
    }

    @Test
    void testFindParkingListWithinRadius() throws ParseException {
        // Mock data
        double longitude = 0.0;
        double latitude = 0.0;
        int radius = 10;

        ParkingEntity parkingEntity = new ParkingEntity(); // Create a sample entity
        ParkingDto parkingDto = new ParkingDto(); // Create a sample DTO
        List<ParkingEntity> entityList = Arrays.asList(parkingEntity);
        List<ParkingDto> dtoList = Arrays.asList(parkingDto);

        // Stub the behavior of parkingService and parkingMapper
        when(parkingFacade.parkingService.findParkingListWithinRadius(longitude, latitude, radius)).thenReturn(entityList);
        when(parkingFacade.parkingMapper.mapEntitiesToDTOs(entityList)).thenReturn(dtoList);

        // Call the method to be tested
        ParkingResponse response = parkingFacade.findParkingListWithinRadius(longitude, latitude, radius);

        // Verify that parkingService and parkingMapper methods were called with the correct parameters
        verify(parkingFacade.parkingService).findParkingListWithinRadius(longitude, latitude, radius);
        verify(parkingFacade.parkingMapper).mapEntitiesToDTOs(entityList);

        // Assertions
        assertEquals(dtoList, response.getResult());
        assertEquals(dtoList.size(), response.getTotalItems());
    }
}

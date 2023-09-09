package com.instantsytem.parkingservice.controller;

import com.instantsytem.parkingservice.dto.ParkingDto;
import com.instantsytem.parkingservice.dto.ParkingResponse;
import com.instantsytem.parkingservice.facade.ParkingFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.io.ParseException;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
class ParkingControllerTest {

    private ParkingController parkingController;
    private ParkingFacade parkingFacade;

    @BeforeEach
    public void setUp() {
        parkingFacade = mock(ParkingFacade.class);
        parkingController = new ParkingController();
        parkingController.parkingFacade = parkingFacade;
    }

    @Test
    void testGetParking() throws ParseException {
        // Mock data
        double longitude = 0.0;
        double latitude = 0.0;
        int radius = 10;
        //Create an entity
        ParkingDto entity = new ParkingDto();
        entity.setName("Sample Parking");
        entity.setAddress("123 Main Street, City");
        entity.setUrl("https://example.com/parking");
        entity.setCapacity(100);
        entity.setPlacesAvailable(50);
        // Create a sample ParkingResponse
        ParkingResponse parkingResponse = new ParkingResponse();
        parkingResponse.setTotalItems(1);
        parkingResponse.setResult(Arrays.asList(entity));


        // Stub the behavior of parkingFacade to return the sample ParkingResponse
        when(parkingFacade.findParkingListWithinRadius(longitude, latitude, radius)).thenReturn(parkingResponse);

        // Call the method to be tested
        ParkingResponse result = parkingController.getParking(longitude, latitude, radius);

        // Verify that parkingFacade method was called with the correct parameters
        verify(parkingFacade).findParkingListWithinRadius(longitude, latitude, radius);

        // Check the first ParkingDto in the result list
        assertFalse(result.getResult().isEmpty()); // Ensure the result list is not empty
        ParkingDto parkingDto = result.getResult().get(0);
        assertEquals("Sample Parking", parkingDto.getName()); // Verify the name of the first entity
        assertEquals("123 Main Street, City", parkingDto.getAddress()); // Verify the address
        assertEquals("https://example.com/parking", parkingDto.getUrl()); // Verify the URL
        assertEquals(100, parkingDto.getCapacity()); // Verify the capacity
        assertEquals(50, parkingDto.getPlacesAvailable()); // Verify placesAvailable


    }
}

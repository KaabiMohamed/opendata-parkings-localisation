package com.instantsytem.parkingservice.service;

import com.instantsytem.parkingservice.entity.ParkingEntity;
import com.instantsytem.parkingservice.repository.ParkingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.CoordinateXY;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.locationtech.jts.io.WKTWriter;
import org.springframework.test.context.ActiveProfiles;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
class ParkingServiceTest {

    private ParkingService parkingService;

    private ParkingRepository parkingRepository;

    @BeforeEach
    public void setUp() {
        parkingRepository = mock(ParkingRepository.class);
        parkingService = new ParkingService();
        parkingService.parkingRepository = parkingRepository;
    }


    @Test
    void testFindParkingListWithinRadius() throws ParseException {
        // Mock data
        double longitude = 1.0;
        double latitude = 1.0;
        int radius = 10;
        // Mock entity

        Point point = (Point) new WKTReader().read(WKTWriter.toPoint(new CoordinateXY(longitude, latitude)));

        List<ParkingEntity> mockParkingList = new ArrayList<>();

        ParkingEntity entity = new ParkingEntity();
        entity.setName("Sample Parking");
        entity.setAddress("123 Main Street, City");
        entity.setUrl("https://example.com/parking");
        entity.setCapacity(100);
        entity.setLongitude(-122.789012);
        entity.setLatitude(37.123456);
        entity.setPlacesAvailable(50);
        entity.setGeoPoint2D(new Point2D.Double(0.876543, -24.567890));

        ParkingEntity anotherEntity = new ParkingEntity();
        anotherEntity.setName("Another Parking");
        anotherEntity.setAddress("456 Elm Street, Town");
        anotherEntity.setUrl("https://example.com/another-parking");
        anotherEntity.setCapacity(150);
        anotherEntity.setLongitude(-123.456789);
        anotherEntity.setLatitude(38.987654);
        anotherEntity.setPlacesAvailable(75);
        anotherEntity.setGeoPoint2D(new Point2D.Double(39.876543, -124.567890));

        mockParkingList.add(entity);
        mockParkingList.add(anotherEntity);

        // Stub the behavior of parkingRepository to return mock data
        when(parkingRepository.findParkingWithinRadiusNativeQuery(any(Point.class), anyDouble())).thenReturn(mockParkingList);
        when(parkingService.parkingRepository.findParkingWithinRadiusNativeQuery(any(Point.class), anyDouble())).thenReturn(mockParkingList);

        // Call the method to be tested
        List<ParkingEntity> result = parkingService.findParkingListWithinRadius(longitude, latitude, radius);

        // Verify that the parkingRepository method was called with the correct parameters
        verify(parkingRepository).findParkingWithinRadiusNativeQuery(point, radius);

        // Assertions
        assertEquals(mockParkingList.size(), result.size());
        assertNotNull(result.get(0)); // Ensure that the first parking entity in the result is not null
        assertEquals("Sample Parking", result.get(0).getName());
    }
}

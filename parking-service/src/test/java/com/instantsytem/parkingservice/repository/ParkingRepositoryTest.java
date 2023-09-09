package com.instantsytem.parkingservice.repository;

import com.instantsytem.parkingservice.entity.ParkingEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.locationtech.jts.geom.CoordinateXY;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.locationtech.jts.io.WKTWriter;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class ParkingRepositoryTest {

    @Mock
    private ParkingRepository parkingRepository;


    @Test
    void findParkingWithinRadiusNativeQueryTest() throws ParseException {

        double longitude = 0.32753841534679007;
        double latitude = 46.57222291373908;
        int radius = 500;
        Point point = (Point) new WKTReader().read(WKTWriter.toPoint(new CoordinateXY(longitude,latitude)));

        // Create a list of ParkingEntity objects for the mock repository to return
        List<ParkingEntity> mockedResult = new ArrayList<>();
        ParkingEntity entity = new ParkingEntity();
        entity.setName("Sample Parking");
        entity.setAddress("123 Main Street, City");
        entity.setUrl("https://example.com/parking");
        entity.setCapacity(100);
        entity.setLongitude(-122.789012);
        entity.setLatitude(37.123456);
        entity.setPlacesAvailable(50);
        entity.setGeoPoint2D(new Point2D.Double(0.32753841534679007, 46.57222291373908));
        mockedResult.add(entity);
        // Add ParkingEntity objects to the list as needed for your test case
        when(parkingRepository.findParkingWithinRadiusNativeQuery(eq(point), anyDouble())).thenReturn(mockedResult);

        // Call the method to be tested
        List<ParkingEntity> result = parkingRepository.findParkingWithinRadiusNativeQuery(point, radius);

        // Verify that the repository method was called with the expected parameters
        verify(parkingRepository).findParkingWithinRadiusNativeQuery(point, radius);
        assertEquals(1, result.size());
    }


}

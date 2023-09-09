package com.instantsystem.connector.global;

import com.instantsystem.connector.global.entity.ParkingEntity;
import com.instantsystem.connector.global.repository.ParkingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.awt.geom.Point2D;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Use the actual database, don't replace it
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD) // Reset the context between tests
@ActiveProfiles("test")
class ParkingRepositoryTest {

    @Mock
    private ParkingRepository parkingRepository;



    @Test
    void testInsertParking() {
        // Create a ParkingEntity
        ParkingEntity entity = new ParkingEntity();
        entity.setGeoPoint2D(new Point2D.Double(1.0, 2.0));
        entity.setAddress("Sample Address");
        entity.setCapacity(100);
        entity.setId("Sample ID");
        entity.setLatitude(3.0);
        entity.setLongitude(4.0);
        entity.setName("Sample Name");
        entity.setParkingId(123L);
        entity.setUrl("Sample URL");

        // Call the insertParking method
        parkingRepository.insertParking(entity);

        // Verify that the method was called with the correct entity
        verify(parkingRepository).insertParking(entity);
    }

    @Test
    void testUpdateParkingEntityWithRealTimeData() {
        // Create a ParkingEntity
        ParkingEntity entity = new ParkingEntity();
        entity.setGeoPoint2D(new Point2D.Double(1.0, 2.0));
        entity.setPlacesAvailable(50);
        // Set other properties as needed

        // Call the updateParkingEntityWithRealTimeData method
        parkingRepository.updateParkingEntityWithRealTimeData(entity);

        // Verify that the method was called with the correct entity
        verify(parkingRepository).updateParkingEntityWithRealTimeData(entity);
    }
}

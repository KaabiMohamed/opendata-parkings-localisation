package com.instantsystem.connector.poitiers.mapper;

import com.instantsystem.connector.global.entity.ParkingEntity;
import com.instantsystem.connector.poitiers.model.Parking;
import com.instantsystem.connector.poitiers.model.ParkingRealTimeData;
import org.locationtech.jts.geom.CoordinateXY;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.locationtech.jts.io.WKTWriter;
import org.springframework.stereotype.Component;

import java.awt.geom.Point2D;

/**
 * CustomMapper: Mapper class for mapping between DTOs and entities.
 */
@Component
public class CustomMapper {

    /**
     * Map a Parking DTO to a ParkingEntity.
     *
     * @param parking The Parking DTO to map.
     * @return A ParkingEntity.
     * @throws ParseException if a parsing error occurs.
     */
    public ParkingEntity map(Parking parking) throws ParseException {
        return ParkingEntity.builder()
                .id(parking.parkingId)
                .url(parking.url)
                .name(parking.name)
                .address(parking.address)
                .latitude(parking.latitude)
                .longitude(parking.longitude)
                .capacity(parking.capacity)
                .geoPoint2D(new Point2D.Double(parking.geoPoint2d.lon, parking.geoPoint2d.lat))
                .gpsPoint(new WKTReader().read(WKTWriter.toPoint(new CoordinateXY(parking.longitude, parking.latitude))))
                .build();
    }

    /**
     * Map a ParkingRealTimeData DTO to a ParkingEntity.
     *
     * @param data The ParkingRealTimeData DTO to map.
     * @return A ParkingEntity.
     */
    public ParkingEntity mapReaTimeDataInParkingEntity(ParkingRealTimeData data) {
        return ParkingEntity.builder()
                .geoPoint2D(data.geoPoint2d != null ? new Point2D.Double(data.geoPoint2d.lon, data.geoPoint2d.lat) : null)
                .placesAvailable(data.placesAvailable)
                .lastUpdateDateBO(data.lastUpdateDateBO)
                .lastUpdateDateDatabase(data.lastUpdateDateDatabase)
                .build();
    }
}

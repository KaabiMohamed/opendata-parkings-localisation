package com.instantsystem.connector.global.repository;


import com.instantsystem.connector.global.entity.ParkingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.awt.geom.Point2D;

/**
 * ParkingRepository class
 */
@Repository
public interface ParkingRepository extends JpaRepository<ParkingEntity,Point2D.Double> {

    /**
     * Insert or update on table parking
     *
     * @param entity parking entity
     */
    @Modifying
    @Transactional
    @Query(value =
            "INSERT INTO parking " +
            "(x,y,address,capacity,gps_point,id,latitude,longitude,name,parking_id,url) " +
            "VALUES " +
            "(" +
            ":#{#entity.geoPoint2D.x},:#{#entity.geoPoint2D.y}," +
            ":#{#entity.address},:#{#entity.capacity},:#{#entity.gpsPoint}," +
            ":#{#entity.id},:#{#entity.latitude}," +
            ":#{#entity.longitude},:#{#entity.name}," +
            ":#{#entity.parkingId},:#{#entity.url} " +
            ") " +
            "ON DUPLICATE KEY UPDATE " +
            "address = VALUES(address), " +
            "capacity = VALUES(capacity), " +
            "gps_point = VALUES(gps_point), " +
            "id = VALUES(id), " +
            "latitude = VALUES(latitude), " +
            "longitude = VALUES(longitude), " +
            "name = VALUES(name), " +
            "parking_id = VALUES(parking_id), " +
            "url = VALUES(url) "
            ,nativeQuery = true)
    void insertParking(@Param("entity") ParkingEntity entity);

    /**
     * UPDATE ParkingEntity with parking realTimeData
     * @param entity parking entity
     */
    @Modifying
    @Transactional
    @Query(value =
            "UPDATE ParkingEntity u " +
            "SET u.placesAvailable = :#{#entity.placesAvailable}, " +
            "u.lastUpdateDateDatabase = :#{#entity.lastUpdateDateDatabase} , " +
            "u.lastUpdateDateBO = :#{#entity.lastUpdateDateBO} " +
            "WHERE u.geoPoint2D = :#{#entity.geoPoint2D}"
            )
    void updateParkingEntityWithRealTimeData(@Param("entity") ParkingEntity entity);
}

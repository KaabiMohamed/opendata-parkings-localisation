package com.instantsytem.parkingservice.repository;


import com.instantsytem.parkingservice.entity.ParkingEntity;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.geom.Point2D;
import java.util.List;

/**
 * This class is the Parking repository
 */
@Repository
public interface ParkingRepository extends JpaRepository<ParkingEntity,Point2D.Double> {

    /**
     *
     * Retrieve all parking's on a radius starting from a point
     *
     * @param targetPoint Geometric point that is used as actual position
     * @param radius Radius of search
     * @return List of parking with distance between target point and returned parking calculation ordered by distance
     */

    @Query(value = "SELECT " +
            "x, y, last_update_datebo, places_available, last_update_date_database, " +
            "address, capacity, gps_point, id, latitude, longitude, name, parking_id, url, " +
            "ROUND(st_distance_sphere(gps_point, :targetPoint)) as distance " +
            "FROM parking " +
            "WHERE st_distance_sphere(gps_point, :targetPoint) <= :radius " +
            "ORDER BY distance ",
            nativeQuery = true
    )    List<ParkingEntity> findParkingWithinRadiusNativeQuery(
            @Param("targetPoint") Point targetPoint,
            @Param("radius") double radius);

}

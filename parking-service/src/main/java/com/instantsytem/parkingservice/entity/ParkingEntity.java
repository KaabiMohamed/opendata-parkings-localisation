package com.instantsytem.parkingservice.entity;

import lombok.*;
import org.locationtech.jts.geom.Geometry;

import javax.persistence.*;
import java.awt.geom.Point2D;
import java.util.Date;

@Entity
@Table(name = "parking")
@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParkingEntity {

    @EmbeddedId
    public Point2D.Double geoPoint2D;
    public String id;
    public Long parkingId;
    public String name;
    public String address;
    public String url;
    public Integer capacity;
    public double longitude;
    public double latitude;
    public Integer placesAvailable;
    public Date lastUpdateDateDatabase;
    public Date lastUpdateDateBO;
    private Geometry gpsPoint;
    private Integer distance;


}

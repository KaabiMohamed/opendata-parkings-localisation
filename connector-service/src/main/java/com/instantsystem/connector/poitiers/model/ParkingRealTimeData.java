package com.instantsystem.connector.poitiers.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@ToString
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ParkingRealTimeData {
    @JsonProperty("nom")
    public String name;
    @JsonProperty("places")
    public int placesAvailable;
    @JsonProperty("capacite")
    public int capacity;
    @JsonProperty("geo_point_2d")
    public GeoPoint2d geoPoint2d;
    @JsonProperty("derniere_mise_a_jour_base")
    public Date lastUpdateDateDatabase;
    @JsonProperty("derniere_actualisation_bo")
    public Date lastUpdateDateBO;
}
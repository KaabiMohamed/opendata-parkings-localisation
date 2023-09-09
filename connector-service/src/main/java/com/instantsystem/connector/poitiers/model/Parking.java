package com.instantsystem.connector.poitiers.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Parking {

    @JsonProperty("id")
    public String parkingId;

    @JsonProperty("nom")
    public String name;

    @JsonProperty("adresse")
    public String address;

    @JsonProperty("url")
    public String url;

    @JsonProperty("nb_places")
    public int capacity;

    @JsonProperty("xlong")
    public double longitude;

    @JsonProperty("ylat")
    public double latitude;

    @JsonProperty("geo_point_2d")
    public GeoPoint2d geoPoint2d;
}
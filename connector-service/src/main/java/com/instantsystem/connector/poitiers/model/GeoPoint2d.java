package com.instantsystem.connector.poitiers.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@ToString
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GeoPoint2d{
    @JsonProperty("lon")
    public double lon;
    @JsonProperty("lat")
    public double lat;
}
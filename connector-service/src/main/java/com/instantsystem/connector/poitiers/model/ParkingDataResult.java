package com.instantsystem.connector.poitiers.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParkingDataResult {
    @JsonProperty("total_count")
    public int totalCount;
    @JsonProperty("results")
    public List<Parking> parkings;
}
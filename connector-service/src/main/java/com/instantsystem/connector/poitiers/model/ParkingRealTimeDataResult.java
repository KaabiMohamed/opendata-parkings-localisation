package com.instantsystem.connector.poitiers.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@ToString
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ParkingRealTimeDataResult {
    @JsonProperty("total_count")
    public int totalCount;
    @JsonProperty("results")
    public List<ParkingRealTimeData> realTimeData;
}
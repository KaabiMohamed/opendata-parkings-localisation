package com.instantsytem.parkingservice.mapper;

import com.instantsytem.parkingservice.controller.ParkingController;
import com.instantsytem.parkingservice.dto.ParkingDto;
import com.instantsytem.parkingservice.entity.ParkingEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is the Parking Mapper
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class ParkingMapper {

    @Autowired
    ModelMapper mapper;

    /**
     * Parking map from Entity to Dto
     *
     * @param parkingEntity ParkingEntity to be mapped
     * @return mapped ParkingDto
     */
    public ParkingDto mapToDTO(ParkingEntity parkingEntity) {
        return mapper.map(parkingEntity, ParkingDto.class);
    }

    /**
     * Map a list of ParkingEntity to ParkingDto
     *
     * @param entities list of entities to map
     * @return mapped DTOs
     */
    public List<ParkingDto> mapEntitiesToDTOs(List<ParkingEntity> entities) {
        return entities.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

}

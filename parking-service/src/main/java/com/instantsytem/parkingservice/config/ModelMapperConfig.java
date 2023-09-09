package com.instantsytem.parkingservice.config;

import com.instantsytem.parkingservice.dto.ParkingDtoGeoPoint2D;
import com.instantsytem.parkingservice.mapper.GeoPointMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.geom.Point2D;

/**
 * This class is ModelMapper Configuration
 *
 */
@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Assign custom mapper for mapping
        modelMapper.createTypeMap(Point2D.Double.class, ParkingDtoGeoPoint2D.class)
                .setConverter(new GeoPointMapper());

        return modelMapper;
    }
}
package com.instantsytem.parkingservice.mapper;

import com.instantsytem.parkingservice.dto.ParkingDtoGeoPoint2D;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import java.awt.geom.Point2D;
import java.math.BigDecimal;

/**
 * This class a custom mapper from Point2D.Double to ParkingDtoGeoPoint2D
 *
 */
public class GeoPointMapper implements Converter<Point2D.Double, ParkingDtoGeoPoint2D> {
    @Override
    public ParkingDtoGeoPoint2D convert(MappingContext<Point2D.Double, ParkingDtoGeoPoint2D> mappingContext) {
        Point2D.Double source = mappingContext.getSource();
        return new ParkingDtoGeoPoint2D().lat(BigDecimal.valueOf(source.x)).lon(BigDecimal.valueOf(source.y));
    }
}

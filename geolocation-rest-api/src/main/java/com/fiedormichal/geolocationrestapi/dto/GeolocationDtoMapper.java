package com.fiedormichal.geolocationrestapi.dto;

import com.fiedormichal.geolocationrestapi.model.Geolocation;

import java.util.List;
import java.util.stream.Collectors;

public class GeolocationDtoMapper {
    private GeolocationDtoMapper(){}

    public static GeolocationDto getGeolocationDto(Geolocation geolocation){
        return GeolocationDto.builder()
                .latitude(geolocation.getLatitude())
                .longitude(geolocation.getLongitude())
                .insertDate(geolocation.getInsertDate())
                .updateDate(geolocation.getUpdateDate())
                .isActive(geolocation.getIsActive())
                .build();
    }

    public static List<GeolocationDto> getGeolocationDtos(List<Geolocation> geolocations){
        return geolocations.stream()
                .map(geolocation -> getGeolocationDto(geolocation))
                .collect(Collectors.toList());
    }
}

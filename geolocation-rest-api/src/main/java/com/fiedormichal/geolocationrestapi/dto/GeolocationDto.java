package com.fiedormichal.geolocationrestapi.dto;

import com.fiedormichal.geolocationrestapi.model.Latitude;
import com.fiedormichal.geolocationrestapi.model.Longitude;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GeolocationDto {
    private Latitude latitude;
    private Longitude longitude;
    private String insertDate;
    private String updateDate;
    private Boolean isActive;
}

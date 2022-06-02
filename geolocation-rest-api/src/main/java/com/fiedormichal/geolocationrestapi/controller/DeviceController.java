package com.fiedormichal.geolocationrestapi.controller;

import com.fiedormichal.geolocationrestapi.dto.GeolocationDtoMapper;
import com.fiedormichal.geolocationrestapi.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/devices")
public class DeviceController {
    private final DeviceService deviceService;

    @GetMapping("/{id}/geolocations")
    public ResponseEntity<Object> getGeolocationsByDeviceId(@PathVariable Integer id){
        return ResponseEntity.ok().body(GeolocationDtoMapper.getGeolocationDtos(deviceService.getAllGeolocationsById(id)));
    }
}

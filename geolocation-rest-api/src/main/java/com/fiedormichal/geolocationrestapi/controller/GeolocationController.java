package com.fiedormichal.geolocationrestapi.controller;

import com.fiedormichal.geolocationrestapi.model.Geolocation;
import com.fiedormichal.geolocationrestapi.service.GeolocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/geolocations")
@Log4j2
public class GeolocationController {
    private final GeolocationService geoLocationService;

    @PostMapping(value = "/save", consumes = {"application/json"})
    public ResponseEntity<Object> saveGeolocation(@Valid @RequestBody Geolocation geolocation){
        Geolocation savedGeolocation = geoLocationService.save(geolocation);
        log.info("Geolocation for device with id = " + savedGeolocation.getDevice().getId() + " has been successfully saved.");
        return ResponseEntity.ok().body(savedGeolocation);
    }
}

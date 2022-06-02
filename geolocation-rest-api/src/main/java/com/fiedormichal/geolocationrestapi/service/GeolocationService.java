package com.fiedormichal.geolocationrestapi.service;

import com.fiedormichal.geolocationrestapi.exception.DeviceNotExistsException;
import com.fiedormichal.geolocationrestapi.model.*;
import com.fiedormichal.geolocationrestapi.repository.GeoLocationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class GeolocationService {
    private final GeoLocationRepository geoLocationRepository;
    private final DeviceService deviceService;
    @Transactional
    public Geolocation save(Geolocation geoLocation){
        Device device = deviceService.getById(geoLocation.getDevice().getId());
        setInactiveGeolocationAsNeeded(device.getId());
        geoLocation.setDevice(device);
        return geoLocationRepository.save(geoLocation);
    }

    private void setInactiveGeolocationAsNeeded(Integer deviceId){
        Optional<Geolocation> activeGeolocation = getActiveGeolocationForDevice(deviceId);
        if (activeGeolocation.isPresent())
        {
            activeGeolocation.get().setIsActive(false);
            activeGeolocation.get().setUpdateDate(LocalDateTime.now());
            log.info("Geolocation with id = " + activeGeolocation.get().getId() + " has been set as not active.");
        }
    }
    public Optional<Geolocation> getActiveGeolocationForDevice(Integer deviceId){
        return geoLocationRepository.findByDeviceIdAndIsActive(deviceId, true);
    }
}

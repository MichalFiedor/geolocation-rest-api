package com.fiedormichal.geolocationrestapi.service;

import com.fiedormichal.geolocationrestapi.exception.DeviceNotExistsException;
import com.fiedormichal.geolocationrestapi.model.Device;
import com.fiedormichal.geolocationrestapi.model.Geolocation;
import com.fiedormichal.geolocationrestapi.repository.DeviceRepository;
import com.fiedormichal.geolocationrestapi.repository.GeoLocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceService {
    private final DeviceRepository deviceRepository;
    private final GeoLocationRepository geoLocationRepository;

    public Device getById(Integer id){
        return deviceRepository.findById(id).orElseThrow(
                () -> new DeviceNotExistsException("Device with id = " + id + " not exists"));
    }

    public List<Geolocation> getAllGeolocationsById(Integer deviceId){
        return geoLocationRepository.findByDeviceId(deviceId);
    }
}

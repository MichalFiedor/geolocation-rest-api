package com.fiedormichal.geolocationrestapi.service;

import com.fiedormichal.geolocationrestapi.exception.DeviceNotExistsException;
import com.fiedormichal.geolocationrestapi.model.*;
import com.fiedormichal.geolocationrestapi.repository.GeoLocationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class GeolocationServiceTest {

    @Autowired
    private GeolocationService geolocationService;
    @MockBean
    private DeviceService deviceService;
    @MockBean
    private GeoLocationRepository geoLocationRepository;

    @Test
    void givenDeviceIdWhichNotExistsWhenSaveThenThrowDeviceNotExistsException() {
        Geolocation geolocation = new Geolocation();
        DeviceType deviceType = new DeviceType();
        deviceType.setId(1);
        deviceType.setName("Phone");
        Device device = new Device();
        device.setId(1);
        geolocation.setDevice(device);
        //when
        when(deviceService.getById(1)).thenThrow(DeviceNotExistsException.class);
        //then
        assertThrows(DeviceNotExistsException.class, () -> geolocationService.save(geolocation));
    }

    @Test
    void givenNewGeolocationToDeviceWithActiveGeolocationWhenSaveThenOldGeolocationIsInactive() {
        Geolocation geolocation1 = new Geolocation();
        DeviceType deviceType = new DeviceType();
        deviceType.setId(1);
        deviceType.setName("Phone");
        Device device = new Device();
        device.setId(1);
        device.setName("Test");
        device.setDeviceType(deviceType);
        Latitude latitude = new Latitude();
        latitude.setLatitudeValue(85.34);
        latitude.setWorldSide("N");
        Longitude longitude = new Longitude();
        longitude.setLongitudeValue(124.34);
        longitude.setWorldSide("E");
        geolocation1.setDevice(device);
        geolocation1.setLatitude(latitude);
        geolocation1.setLongitude(longitude);

        Geolocation geolocation2 = new Geolocation();
        geolocation2.setDevice(device);
        geolocation2.setLatitude(latitude);
        geolocation2.setLongitude(longitude);
        geolocation2.setIsActive(true);
        //when
        when(deviceService.getById(1)).thenReturn(device);
        when(geoLocationRepository.findByDeviceIdAndIsActive(device.getId(), true)).thenReturn(Optional.of(geolocation2));
        geolocationService.save(geolocation1);
        //then
        assertFalse(geolocation2.getIsActive());
    }
}
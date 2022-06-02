package com.fiedormichal.geolocationrestapi.controller;

import com.fiedormichal.geolocationrestapi.model.*;
import com.fiedormichal.geolocationrestapi.service.DeviceService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@WithMockUser(username = "user")
class DeviceControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DeviceService deviceService;

    @Test
    void givenDeviceIdWhenGetGeolocationsByDeviceIdThenReturnStatusOkAndGeolocationsList() throws Exception {
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
        latitude.setId(1);
        Longitude longitude = new Longitude();
        longitude.setLongitudeValue(124.34);
        longitude.setWorldSide("E");
        longitude.setId(1);
        geolocation1.setDevice(device);
        geolocation1.setLatitude(latitude);
        geolocation1.setLongitude(longitude);

        Geolocation geolocation2 = new Geolocation();
        Latitude latitude2 = new Latitude();
        latitude.setLatitudeValue(85.34);
        latitude.setWorldSide("N");
        latitude.setId(2);
        Longitude longitude2 = new Longitude();
        longitude.setLongitudeValue(124.34);
        longitude.setWorldSide("E");
        longitude.setId(2);
        geolocation2.setDevice(device);
        geolocation2.setLatitude(latitude2);
        geolocation2.setLongitude(longitude2);

        when(deviceService.getAllGeolocationsById(1)).thenReturn(Arrays.asList(geolocation1, geolocation2));

        mockMvc.perform(get("/devices/{id}/geolocations", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.length()", is(2)));
    }
}
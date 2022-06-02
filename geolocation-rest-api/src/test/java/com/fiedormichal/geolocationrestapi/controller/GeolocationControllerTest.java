package com.fiedormichal.geolocationrestapi.controller;

import com.fiedormichal.geolocationrestapi.service.DeviceService;
import com.fiedormichal.geolocationrestapi.service.GeolocationService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@WithMockUser(username = "user")
class GeolocationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void givenGeolocationWhenSaveGeolocationThenReturnStatusOk() throws Exception {
        JSONObject geolocation = new JSONObject();
        JSONObject device = new JSONObject();
        device.put("id",1);
        JSONObject latitude = new JSONObject();
        latitude.put("latitudeValue", 88.35);
        latitude.put("worldSide", "N");
        JSONObject longitude = new JSONObject();
        longitude.put("longitudeValue", -39.365);
        longitude.put("worldSide", "E");
        geolocation.put("device", device);
        geolocation.put("latitude", latitude);
        geolocation.put("longitude", longitude);

        mockMvc.perform(post("/geolocations/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(geolocation.toString()))
                        .andDo(print())
                        .andExpect(status().isOk());
    }

    @Test
    void givenIncorrectWorldSideOfLongitudeWhenSaveGeolocationThenReturnStatusBadRequest() throws Exception {
        JSONObject geolocation = new JSONObject();
        JSONObject device = new JSONObject();
        device.put("id",1);
        JSONObject latitude = new JSONObject();
        latitude.put("latitudeValue", 88.35);
        latitude.put("worldSide", "N");
        JSONObject longitude = new JSONObject();
        longitude.put("longitudeValue", -39.365);
        longitude.put("worldSide", "N");
        geolocation.put("device", device);
        geolocation.put("latitude", latitude);
        geolocation.put("longitude", longitude);

        mockMvc.perform(post("/geolocations/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(geolocation.toString()))
                        .andDo(print())
                        .andExpect(status().isBadRequest());
    }

    @Test
    void givenLatitudeValueGreaterThanUpperLimitWhenSaveGeolocationThenReturnStatusBadRequest() throws Exception {
        JSONObject geolocation = new JSONObject();
        JSONObject device = new JSONObject();
        device.put("id",1);
        JSONObject latitude = new JSONObject();
        latitude.put("latitudeValue", 95.35);
        latitude.put("worldSide", "N");
        JSONObject longitude = new JSONObject();
        longitude.put("longitudeValue", -39.365);
        longitude.put("worldSide", "E");
        geolocation.put("device", device);
        geolocation.put("latitude", latitude);
        geolocation.put("longitude", longitude);

        mockMvc.perform(post("/geolocations/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(geolocation.toString()))
                        .andDo(print())
                        .andExpect(status().isBadRequest());
    }

    @Test
    void givenLatitudeValueLowerThanUpperLimitWhenSaveGeolocationThenReturnStatusBadRequest() throws Exception {
        JSONObject geolocation = new JSONObject();
        JSONObject device = new JSONObject();
        device.put("id",1);
        JSONObject latitude = new JSONObject();
        latitude.put("latitudeValue", -92.35);
        latitude.put("worldSide", "N");
        JSONObject longitude = new JSONObject();
        longitude.put("longitudeValue", -39.365);
        longitude.put("worldSide", "E");
        geolocation.put("device", device);
        geolocation.put("latitude", latitude);
        geolocation.put("longitude", longitude);

        mockMvc.perform(post("/geolocations/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(geolocation.toString()))
                        .andDo(print())
                        .andExpect(status().isBadRequest());
    }

    @Test
    void givenLongitudeValueGreaterThanUpperLimitWhenSaveGeolocationThenReturnStatusBadRequest() throws Exception {
        JSONObject geolocation = new JSONObject();
        JSONObject device = new JSONObject();
        device.put("id",1);
        JSONObject latitude = new JSONObject();
        latitude.put("latitudeValue", 88.35);
        latitude.put("worldSide", "N");
        JSONObject longitude = new JSONObject();
        longitude.put("longitudeValue", 185.365);
        longitude.put("worldSide", "E");
        geolocation.put("device", device);
        geolocation.put("latitude", latitude);
        geolocation.put("longitude", longitude);

        mockMvc.perform(post("/geolocations/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(geolocation.toString()))
                        .andDo(print())
                        .andExpect(status().isBadRequest());
    }

    @Test
    void givenLongitudeValueLowerThanUpperLimitWhenSaveGeolocationThenReturnStatusBadRequest() throws Exception {
        JSONObject geolocation = new JSONObject();
        JSONObject device = new JSONObject();
        device.put("id",1);
        JSONObject latitude = new JSONObject();
        latitude.put("latitudeValue", 88.35);
        latitude.put("worldSide", "N");
        JSONObject longitude = new JSONObject();
        longitude.put("longitudeValue", -185.365);
        longitude.put("worldSide", "E");
        geolocation.put("device", device);
        geolocation.put("latitude", latitude);
        geolocation.put("longitude", longitude);

        mockMvc.perform(post("/geolocations/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(geolocation.toString()))
                        .andDo(print())
                        .andExpect(status().isBadRequest());
    }

    @Test
    void givenLongitudeValueIsNullWhenSaveGeolocationThenReturnStatusBadRequest() throws Exception {
        JSONObject geolocation = new JSONObject();
        JSONObject device = new JSONObject();
        device.put("id",1);
        JSONObject latitude = new JSONObject();
        latitude.put("worldSide", "N");
        JSONObject longitude = new JSONObject();
        longitude.put("longitudeValue", 185.365);
        longitude.put("worldSide", "E");
        geolocation.put("device", device);
        geolocation.put("latitude", latitude);
        geolocation.put("longitude", longitude);

        mockMvc.perform(post("/geolocations/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(geolocation.toString()))
                        .andDo(print())
                        .andExpect(status().isBadRequest());
    }

    @Test
    void givenWorldSideIsNullWhenSaveGeolocationThenReturnStatusBadRequest() throws Exception {
        JSONObject geolocation = new JSONObject();
        JSONObject device = new JSONObject();
        device.put("id",1);
        JSONObject latitude = new JSONObject();
        latitude.put("latitudeValue", 88.35);
        JSONObject longitude = new JSONObject();
        longitude.put("longitudeValue", -185.365);
        longitude.put("worldSide", "E");
        geolocation.put("device", device);
        geolocation.put("latitude", latitude);
        geolocation.put("longitude", longitude);

        mockMvc.perform(post("/geolocations/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(geolocation.toString()))
                        .andDo(print())
                        .andExpect(status().isBadRequest());
    }

    @Test
    void givenIncorrectEndPointWhenSaveGeolocationThenReturnStatusNotFound() throws Exception {
        JSONObject geolocation = new JSONObject();
        JSONObject device = new JSONObject();
        device.put("id",1);
        JSONObject latitude = new JSONObject();
        latitude.put("latitudeValue", 88.35);
        latitude.put("worldSide", "N");
        JSONObject longitude = new JSONObject();
        longitude.put("longitudeValue", -185.365);
        longitude.put("worldSide", "E");
        geolocation.put("device", device);
        geolocation.put("latitude", latitude);
        geolocation.put("longitude", longitude);

        mockMvc.perform(post("/geolocations/savse")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(geolocation.toString()))
                        .andDo(print())
                        .andExpect(status().isNotFound());
    }

    @Test
    void givenMalformedJsonWhenSaveGeolocationThenReturnStatusBadRequest() throws Exception {
        JSONObject geolocation = new JSONObject();
        JSONObject device = new JSONObject();
        device.put("id",1);
        JSONObject latitude = new JSONObject();
        latitude.put("latitudeValue", true);
        latitude.put("worldSide", "N");
        JSONObject longitude = new JSONObject();
        longitude.put("longitudeValue", -185.365);
        longitude.put("worldSide", "E");
        geolocation.put("device", device);
        geolocation.put("latitude", latitude);
        geolocation.put("longitude", longitude);

        mockMvc.perform(post("/geolocations/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(geolocation.toString()))
                        .andDo(print())
                        .andExpect(status().isBadRequest());
    }

    @Test
    void givenUnsupportedMediaTypeWhenSaveGeolocationThenReturnStatusBadRequest() throws Exception {
        JSONObject geolocation = new JSONObject();
        JSONObject device = new JSONObject();
        device.put("id",1);
        JSONObject latitude = new JSONObject();
        latitude.put("latitudeValue", true);
        latitude.put("worldSide", "N");
        JSONObject longitude = new JSONObject();
        longitude.put("longitudeValue", -185.365);
        longitude.put("worldSide", "E");
        geolocation.put("device", device);
        geolocation.put("latitude", latitude);
        geolocation.put("longitude", longitude);

        mockMvc.perform(post("/geolocations/save")
                        .contentType(MediaType.APPLICATION_ATOM_XML)
                        .content(geolocation.toString()))
                        .andDo(print())
                        .andExpect(status().isUnsupportedMediaType());
    }
}
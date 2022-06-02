package com.fiedormichal.geolocationrestapi.repository;

import com.fiedormichal.geolocationrestapi.model.Device;
import com.fiedormichal.geolocationrestapi.model.Geolocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {
}

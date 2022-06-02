package com.fiedormichal.geolocationrestapi.repository;

import com.fiedormichal.geolocationrestapi.model.Geolocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GeoLocationRepository extends JpaRepository<Geolocation, Integer> {
    List<Geolocation> findByDeviceId(Integer id);
    Optional<Geolocation> findByDeviceIdAndIsActive(Integer deviceId, boolean isActive);

}

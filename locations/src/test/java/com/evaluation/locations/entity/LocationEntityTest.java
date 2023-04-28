package com.evaluation.locations.entity;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.evaluation.locations.repository.LocationRepository;

@DataJpaTest
public class LocationEntityTest {

    @Autowired
    private LocationRepository locationRepository;

    @Test
    public void saveLocationTests() {
        LocationEntity locationEntity = new LocationEntity(
            (long)1,
            "7493", 
            "Pinamar"
          );
          LocationEntity savedLocationEntity = locationRepository.save(locationEntity);
        assertNotNull(savedLocationEntity);
    }
}

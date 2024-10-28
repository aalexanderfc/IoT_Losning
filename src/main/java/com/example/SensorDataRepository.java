package com.example;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorDataRepository extends JpaRepository<SensorData, Long> {
    // Lagg till en metod for att hamta den senaste posten
    SensorData findTopByOrderByIdDesc();
}

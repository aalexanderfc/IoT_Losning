package com.example;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorDataRepository extends JpaRepository<SensorData, Long> {
    // Lägg till en metod för att hämta den senaste posten
    SensorData findTopByOrderByIdDesc();
}

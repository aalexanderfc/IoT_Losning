package com.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorDataHistoryRepository extends JpaRepository<SensorDataHistory, Long> {
}

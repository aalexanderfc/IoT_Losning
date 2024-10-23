package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class SensorDataController {

    @Autowired
    private SensorDataRepository sensorDataRepository;

    // Endpoint för att ta emot och spara sensordata via POST
    @PostMapping("/sensor_data")
    public ResponseEntity<String> receiveSensorData(@RequestBody SensorData sensorData) {
        sensorDataRepository.save(sensorData);
        return ResponseEntity.ok("Data received and saved");
    }

    // Endpoint för att hämta den senaste sensordatan
    @GetMapping("/sensor_data/last")
    public SensorData getLastSensorData() {
        return sensorDataRepository.findTopByOrderByIdDesc();
    }

    // Endpoint för att hämta all sensordata
    @GetMapping("/sensor_data")
    public List<SensorData> getAllSensorData() {
        return sensorDataRepository.findAll();
    }
}

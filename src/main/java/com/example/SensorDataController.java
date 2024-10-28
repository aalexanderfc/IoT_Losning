package com.example;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sensor_data")
public class SensorDataController {

    @Autowired
    private SensorDataRepository sensorDataRepository;

    @Autowired
    private SensorDataHistoryRepository sensorDataHistoryRepository;

// Endpoint for att ta emot och spara sensordata via POST
@PostMapping
public ResponseEntity<String> receiveSensorData(@RequestBody SensorData sensorData) {
    // Spara data i SensorData-tabellen
    sensorDataRepository.save(sensorData);

    // Spara data i SensorDataHistory-tabellen med en timestamp
    SensorDataHistory historyData = new SensorDataHistory();
    historyData.setSensorId(sensorData.getSensorId());
    historyData.setTemperature(sensorData.getTemperature());
    historyData.setHumidity(sensorData.getHumidity());
    historyData.setTimestamp(new Timestamp(System.currentTimeMillis()));

    sensorDataHistoryRepository.save(historyData);

    return ResponseEntity.ok("Data received and saved in both tables.");
}


    // Endpoint for att hamta den senaste sensordatan
    @GetMapping("/last")
    public SensorData getLastSensorData() {
        return sensorDataRepository.findTopByOrderByIdDesc();
    }

    // Endpoint for att hamta all sensordata
    @GetMapping
    public List<SensorData> getAllSensorData() {
        return sensorDataRepository.findAll();
    }
}

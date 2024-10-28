package com.example;

import java.sql.Timestamp;
import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sensor_data_history")
public class SensorDataHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int sensorId;
    private double temperature;  // �ndra till double
    private double humidity;     // �ndra till double
    private Timestamp timestamp;

    // Konstruktor som automatiskt s�tter timestamp
    public SensorDataHistory() {
        this.timestamp = Timestamp.from(Instant.now());
    }

    // Getters och Setters
    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}

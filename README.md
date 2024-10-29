# IoT_Lösning

## Systemspecifikation för IoT-lösning

Denna IoT-lösning är en prototyp för övervakning av temperatur och luftfuktighet, med säkerhets- och skalbarhetsåtgärder i enlighet med Cyber Resilience Act (CRA).

---

### 1. Arkitektur

- **Komponenter:**
  - **Sensorenhet**: En DHT11-sensor kopplad till en Raspberry Pi 5, som läser av temperatur och luftfuktighet.
  - **Gateway (Raspberry Pi)**: Samlar in data från sensorenheten via GPIO-pins.
  - **Kommunikationsnätverk**: Använder MQTT-protokoll för att skicka data över WiFi till backend.
  - **Backend (Spring Boot & MySQL)**: Tar emot och lagrar sensorinformationen i en MySQL-databas. En Spring Boot-applikation hanterar och bearbetar sensordata.
  - **Visualisering (Grafana)**: Visualiserar historiska och realtidsdata för analys och övervakning.
- **Arkitekturdiagram**: Ett blockdiagram visar alla komponenter och anslutningar mellan sensorenheten, gateway, backend och visualiseringstjänsten (kan inkluderas separat).

---

### 2. Kommunikationsflöde

- **Sensor till Gateway**: Data från sensorenheten skickas via GPIO från DHT11 till Raspberry Pi.
- **Gateway till Backend**: Sensorvärden publiceras via MQTT på en säker kanal och tas emot av en backend-komponent i Spring Boot.
- **Backend till Visualisering**: Backend lagrar data i MySQL och tillhandahåller data till Grafana för visualisering och analys via tidsstämplade loggar.
- **Protokoll och Användning**: MQTT används för kommunikation mellan sensorenhet och backend. För framtida säkerhet är planen att lägga till TLS eller mTLS i MQTT-protokollet.

---

### 3. Säkerhetsåtgärder

- **Nuvarande Säkerhetsmekanismer**:
  - **Lösenordsskydd**: Alla viktiga anslutningar (t.ex. till databasen) är lösenordsskyddade.
  - **Dataövervakning**: Data visualiseras och övervakas i Grafana för att tidigt kunna upptäcka avvikelser.

- **Framtida Säkerhetsplaner**:
  - **mTLS/TLS för MQTT**: Planerat att införa mTLS eller TLS för att kryptera data under överföring.
  - **Certifikatbaserad autentisering**: Implementera certifikat för att säkra anslutningar och tillåta endast auktoriserade enheter att komm

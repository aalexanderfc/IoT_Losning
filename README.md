# IoT-lösningsprototyp

## Översikt

Denna IoT-lösning är en prototyp för övervakning av temperatur och luftfuktighet, utformad med säkerhets- och skalbarhetsåtgärder i enlighet med kraven från Cyber Resilience Act (CRA). Lösningen säkerställer säker kommunikation och följer riktlinjerna för fjärrövervakning av sensordata.

# IoT Temperature and Humidity Monitoring System

Ett IoT-projekt som använder en DHT11-sensor kopplad till en Raspberry Pi för att mäta temperatur och luftfuktighet, med lagring i MySQL via en Spring Boot-backend. Grafana används för att visualisera sensordata.

## Förutsättningar

- Raspberry Pi 5 (eller annan kompatibel enhet)
- DHT11-sensor
- Python 3 och virtuellt miljöverktyg (`venv`)
- Java (minst version 11)
- MySQL
- Mosquitto MQTT Broker
- Grafana
- Git

---

## 1. Arkitektur

- **Komponenter:**
  - **Sensorenhet:** DHT11-sensor kopplad till en Raspberry Pi 5, som läser av temperatur och luftfuktighet.
  - **Gateway (Raspberry Pi):** Samlar in data från sensorenheten via GPIO-pins.
  - **Kommunikationsnätverk:** Använder MQTT-protokollet över WiFi för att skicka data till backend.
  - **Backend (Spring Boot & MySQL):** Tar emot och lagrar sensordata i en MySQL-databas. En Spring Boot-applikation hanterar och bearbetar sensordata.
  - **Visualisering (Grafana):** Tillhandahåller historiska och realtidsvisualiseringar för analys och övervakning.

- **Arkitekturdiagram:** Ett blockdiagram visar alla komponenter och anslutningar mellan sensorenheten, gateway, backend och visualiseringstjänsten (kan inkluderas separat).

---

## 2. Kommunikationsflöde

- **Sensor till Gateway:** Data överförs från DHT11-sensorn till Raspberry Pi via GPIO.
- **Gateway till Backend:** Sensorvärden publiceras säkert via MQTT och tas emot av backend-komponenten i Spring Boot.
- **Backend till Visualisering:** Data lagras i MySQL och tillhandahålls till Grafana för visualisering och loggning med tidsstämplar.
- **Protokoll och Användning:** MQTT används för kommunikationen mellan sensorenheten och backend. För framtida säkerhet är planen att införa TLS eller mTLS för säker MQTT-kommunikation.

---

## 3. Säkerhetsåtgärder

### Nuvarande Säkerhetsmekanismer
- **Lösenordsskydd:** Alla kritiska anslutningar (t.ex. till databasen) är lösenordsskyddade.
- **Dataövervakning:** Data visualiseras och övervakas i Grafana för att upptäcka avvikelser.

### Framtida Säkerhetsplaner
- **mTLS/TLS för MQTT:** Plan att införa mTLS eller TLS för att kryptera data under överföring.
- **Certifikatbaserad autentisering:** Implementera certifikat för att säkra anslutningar och endast tillåta auktoriserade enheter att kommunicera med backend.

---

## 4. Cyber Resilience Act (CRA) Kravuppfyllande

### Säkerhet-by-design
- **Säker kommunikationsväg:** Framtida plan för mTLS/TLS.
- **Lösenordskontroller:** Lösenordsskydd på backend och databaskopplingar.
- **Rollbaserad åtkomst (Framtid):** Framtida versioner begränsar åtkomst till backend och grafiska gränssnitt baserat på roller, vilket begränsar åtkomsten till kritiska komponenter.

### Uppdaterbarhet
- **Certifikatförnyelseplan:** Certifikat kommer kunna förnyas genom en automatiserad process.
- **OTA (Over-the-Air) Uppdateringar:** För att möjliggöra fjärruppdateringar kommer framtida mekanismer stödja OTA-uppdateringar, vilket låter sensorer och gateways uppdateras utan fysisk åtkomst.

### Sårbarhetshantering
- **Övervakning med Grafana:** Visar avvikelser i data som kan indikera säkerhetsproblem.
- **Säkerhetsskanning:** Regelbundna skanningar för att identifiera kända sårbarheter i systembibliotek och mjukvarukomponenter.
- **Incidenthantering:** En plan utvecklas för att hantera och dokumentera säkerhetsincidenter. Loggar sparas för att kunna utvärdera framtida säkerhetsåtgärder och förbättra sårbarhetshanteringen.

---

## Framtida Förbättringar

- **Implementera TLS/mTLS** för MQTT-kommunikation.
- **Utöka OTA-uppdateringskapacitet** för fjärruppdateringar.
- **Aktivera Rollbaserad Åtkomstkontroll** för att begränsa åtkomst baserat på användarroller.
- **Automatisera Certifikatförnyelse** för kontinuerlig säker kommunikation.

## Installation

### 1. Klona GitHub-repositoryt

```bash
git clone https://github.com/aalexanderfc/IoT_Losning.git
cd IoT_losning
```
### 2. Installera och konfigurera MySQL
Installera MySQL:
```bash
sudo apt update
sudo apt install mysql-server
```
**Skapa en MySQL-databas och användare:**
```sql
CREATE DATABASE sensor_data;
CREATE USER 'dbuser'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON sensor_data.* TO 'dbuser'@'localhost';
FLUSH PRIVILEGES;
```
**Konfigurera Spring Boot:** Uppdatera application.properties i Spring Boot-projektet för att matcha databasen:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/sensor_data
spring.datasource.username=dbuser
spring.datasource.password=password
```

### 3. Installera och starta Mosquitto
**Installera Mosquitto:**
```bash
sudo apt install mosquitto mosquitto-clients
```
**Verifiera att Mosquitto körs:**
```bash
sudo systemctl status mosquitto
```
**Testa Mosquitto:**
Publicera ett meddelande:
```bash
mosquitto_pub -h localhost -t "test/topic" -m "Hello, Mosquitto!"
```
**Prenumerera på ett ämne:**
```bash
mosquitto_sub -h localhost -t "test/topic"
```
### 4. Installera och konfigurera Python-miljön
Skapa och aktivera en virtuell miljö:
```bash
python3 -m venv myenv
source myenv/bin/activate
```
**Installera nödvändiga Python-paket:**
```bash
pip install paho-mqtt adafruit-circuitpython-dht
```
**Koppla DHT11-sensorn och kör Python-skriptet:**
Kör dht11_read.py för att läsa och publicera data från DHT11 till MQTT:
```bash
python3 adafruit_dht11_read.py
```
### 5. Bygg och kör Spring Boot-backend
**1.Bygg projektet:**
```bash
./mvnw clean install
```
**2.Starta Spring Boot:**
```bash
java -jar target/sensor-data-0.0.1-SNAPSHOT.jar
```
**3.Verifiera API-anslutningarna:**
POST och GET-ändpunkter för att hämta och lagra sensordata finns tillgängliga. Testa med verktyg som Postman eller curl.

### 6. Installera och starta Grafana
**1.Installera Grafana:**
```bash
sudo apt install -y grafana
```
**2.Starta Grafana:**
```bash
sudo systemctl start grafana-server
```
**3.Öppna Grafana i webbläsaren:** Gå till http://localhost:3000 och logga in med standarduppgifterna (admin/admin).

**4.Lägg till MySQL-databas i Grafana:**
-Gå till **Configuration > Data Sources > Add Data Source.**
-Välj MySQL och ange anslutningsinformationen.

**5.Skapa en dashboard för att visualisera sensordata:**
Skapa paneler och använd SQL-frågor för att hämta temperatur- och luftfuktighetsdata med tidsstämplar.

## Felsökning

**Mosquitto-anslutning:** Om anslutningen misslyckas, kontrollera brandväggsinställningar och bekräfta att Mosquitto körs.
**Spring Boot-databasanslutning:** Kontrollera att MySQL-tjänsten körs och att användarens behörigheter är korrekt inställda.
**Grafana-åtkomst:** Om Grafana inte är åtkomligt, verifiera att port 3000 är öppen i brandväggen.



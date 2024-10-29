# IoT_Lösning

Systemspecifikation för IoT-lösning

1. Arkitektur
•	Komponenter:
o	Sensorenhet: En DHT11-sensor kopplad till en Raspberry Pi 5, som läser av temperatur och luftfuktighet.
o	Gateway (Raspberry Pi): Fungerar som en gateway och samlar in data från sensorenheten via GPIO-pins.
o	Kommunikationsnätverk: MQTT-protokoll används för att skicka data över WiFi till backend.
o	Backend (Spring Boot & MySQL): Samlar och lagrar sensorinformationen i en MySQL-databas via en Spring Boot-applikation som hanterar och bearbetar sensoruppgifter.
o	Visualisering (Grafana): Används för att visa historiska och realtidsdata för att möjliggöra analys och övervakning.
•	Arkitekturdiagram: Ett blockdiagram som visar alla komponenter och anslutningar mellan sensorenheten, gateway, backend och visualiseringstjänsten kan inkluderas för att ge en grafisk översikt.

3. Kommunikationsflöde
•	Sensor till Gateway: Data från sensorenheten skickas via GPIO från DHT11 till Raspberry Pi.
•	Gateway till Backend: Sensorvärden publiceras via MQTT på en säker kanal och tas emot av en backend-mottagare i Spring Boot.
•	Backend till Visualisering: Backend lagrar data i MySQL och tillhandahåller data till Grafana för visualisering och analys via tidsstämplade loggar.
•	Protokoll och Användning: MQTT används för kommunikation mellan sensorenhet och backend. För framtida säkerhet kan TLS eller mTLS läggas till i MQTT-protokollet för säkerhetsförbättring.
4. Säkerhetsåtgärder
   
•	Nuvarande Säkerhetsmekanismer:
o	Lösenordsskydd: Alla viktiga anslutningar (t.ex. till databasen) är lösenordsskyddade.
o	Dataövervakning: Data visualiseras och övervakas i Grafana för att tidigt kunna upptäcka avvikelser.
•	Framtida Säkerhetsplaner:
o	mTLS/TLS för MQTT: Planera för att lägga till mTLS eller TLS för att kryptera data under överföring.
o	Certifikatbaserad autentisering: Implementera certifikat för att säkra anslutningar och tillåta endast auktoriserade enheter att kommunicera med backend.

6. Cyber Resilience Act (CRA) Kravuppfyllande
•	Säkerhet-by-design:
o	Säker kommunikationsväg genom framtida plan för mTLS/TLS.
o	Lösenordskontroller på backend och databaskopplingar.
o	Rollbaserad åtkomst till backend och grafiska gränssnitt i framtida versioner för att begränsa åtkomst till kritiska komponenter.
•	Uppdaterbarhet:
o	Plan för Uppdatering av Certifikat: Certifikat kommer att kunna förnyas genom en automatiserad process.
o	OTA (Over-the-Air) Uppdatering: För att möjliggöra uppdateringar på distans kan en framtida OTA-uppdateringsmekanism införas som låter sensorer och gateways uppdateras utan fysisk åtkomst.

•	Sårbarhetshantering:
o	Övervakning med Grafana: Visar avvikelser i data som kan indikera säkerhetsproblem.
o	Säkerhetsskanning: Regelbundna skanningar för att upptäcka kända sårbarheter i systemets bibliotek och mjukvarukomponenter.
o	Incidenthantering: Skapa en handlingsplan för hur säkerhetsincidenter ska hanteras och dokumenteras om de inträffar. Incidenter loggas för att kunna utvärdera framtida säkerhetsåtgärder och förbättra sårbarhetshanteringen.



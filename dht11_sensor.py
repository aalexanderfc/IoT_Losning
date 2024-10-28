import Adafruit_DHT
import time

# Använd DHT11 och GPIO 4
DHT_SENSOR = Adafruit_DHT.DHT11
DHT_PIN = 4

while True:
    humidity, temperature = Adafruit_DHT.read(DHT_SENSOR, DHT_PIN)
    
    if humidity is not None and temperature is not None:
        print(f"Temperature: {temperature}C, Humidity: {humidity}%")
    else:
        print("Failed to retrieve data from sensor")
    
    time.sleep(2)  # Läser sensordata varannan sekund

import Adafruit_DHT
import paho.mqtt.client as mqtt
import json
import time
import os

# Forcing Adafruit_DHT to recognize Raspberry Pi platform
os.environ["BLINKA_FORCEBOARD"] = "RASPBERRY_PI_4B"  # Specify the exact Raspberry Pi model
os.environ["BLINKA_FORCECHIP"] = "BCM2XXX"  # Force Broadcom chip detection

# Sensor and MQTT configurations
sensor = Adafruit_DHT.DHT11
pin = 4  # Adjust to your GPIO pin for the DHT11 sensor
mqtt_broker = "localhost"
mqtt_port = 1883
mqtt_topic = "test/topic"

# Function to read from DHT11 sensor and publish to MQTT
def read_and_publish():
    humidity, temperature = Adafruit_DHT.read_retry(sensor, pin)
    if humidity is not None and temperature is not None:
        data = json.dumps({
            "temperature": temperature,
            "humidity": humidity
        })
        client.publish(mqtt_topic, data)
        print(f"Published data: {data}")
    else:
        print("Failed to get reading from the sensor.")

# MQTT client setup
client = mqtt.Client()
client.connect(mqtt_broker, mqtt_port)

# Loop to read and publish data every 10 seconds
try:
    while True:
        read_and_publish()
        time.sleep(10)
except KeyboardInterrupt:
    print("Stopped by user")

from gpiozero import DHT11
from signal import pause
import paho.mqtt.client as mqtt
import json
import time

# Set up the sensor on the specified pin
pin = 4  # Adjust to your GPIO pin for the DHT11 sensor
sensor = DHT11(pin)

# MQTT configurations
mqtt_broker = "localhost"
mqtt_port = 1883
mqtt_topic = "test/topic"

# Set up MQTT client
client = mqtt.Client()
client.connect(mqtt_broker, mqtt_port)

def read_and_publish():
    humidity = sensor.humidity
    temperature = sensor.temperature
    if humidity is not None and temperature is not None:
        data = json.dumps({
            "temperature": temperature,
            "humidity": humidity
        })
        client.publish(mqtt_topic, data)
        print(f"Published data: {data}")
    else:
        print("Failed to get reading from the sensor.")

# Loop to read and publish data every 10 seconds
try:
    while True:
        read_and_publish()
        time.sleep(10)
except KeyboardInterrupt:
    print("Stopped by user")

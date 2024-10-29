import time
import paho.mqtt.client as mqtt
import adafruit_dht
import board

# Initialize the DHT11 sensor
dht_device = adafruit_dht.DHT11(board.D4)

# Configure the MQTT client
mqtt_client = mqtt.Client()
mqtt_client.connect("localhost", 1883)  # Update with the MQTT broker’s IP if it's remote

def publish_data():
    try:
        temperature = dht_device.temperature
        humidity = dht_device.humidity
        if temperature is not None and humidity is not None:
            # Publish data to MQTT
            mqtt_client.publish("sensor/temperature", temperature)
            mqtt_client.publish("sensor/humidity", humidity)
            print(f"Published temperature: {temperature}°C, humidity: {humidity}%")
        else:
            print("Failed to retrieve data from DHT11")
    except RuntimeError as e:
        print(f"Error reading DHT11: {e}")

while True:
    publish_data()
    time.sleep(10)  # Publish every 10 seconds

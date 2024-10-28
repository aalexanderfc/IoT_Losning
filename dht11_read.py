import adafruit_dht
import board
import paho.mqtt.client as mqtt

# DHT11-sensorinställningar
dhtDevice = adafruit_dht.DHT11(board.D4)

# MQTT-inställningar
broker_address = "localhost"
port = 1883
client = mqtt.Client("DHT11Publisher", protocol=mqtt.MQTTv311) 
# Anslut till MQTT-brokern
client.connect(broker_address, port=port)

# Läsa av sensorvärden och publicera till MQTT
try:
    humidity = dhtDevice.humidity
    temperature = dhtDevice.temperature

    if humidity is not None and temperature is not None:
        message = f"Temperature: {temperature}C, Humidity: {humidity}%"
        print(f"Publishing: {message}")

        # Publicera till MQTT
        client.publish("sensor/dht11", message)
    else:
        print("Failed to retrieve data from DHT11 sensor")

except RuntimeError as error:
    print(f"Runtime error: {error}")
except Exception as e:
    dhtDevice.exit()
    print(f"General error: {e}")

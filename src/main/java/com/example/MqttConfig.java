import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultMqttPahoClientFactory;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessagingException;
import org.springframework.integration.channel.DirectChannel;

@Configuration
public class MqttConfig {

    @Bean
    public DefaultMqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setServerURIs("tcp://localhost:1883");  // Adjust to your broker URL
        return factory;
    }

    @Bean
    public MqttPahoMessageDrivenChannelAdapter adapter() {
        MqttPahoMessageDrivenChannelAdapter adapter = 
                new MqttPahoMessageDrivenChannelAdapter("client-id", mqttClientFactory(), "sensor/temperature", "sensor/humidity");
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @ServiceActivator(inputChannel = "mqttInputChannel")
    public void messageHandler(String data) throws MessagingException {
        System.out.println("Received MQTT data: " + data);
        // Process and save data into your database here
    }
}

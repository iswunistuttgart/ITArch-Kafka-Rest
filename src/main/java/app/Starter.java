package app;
import com.google.gson.Gson;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Properties;
import java.util.UUID;

@SpringBootApplication
public class Starter {
    public static KafkaConsumer<String, String> consumer;
    public static String GENERAL_MESSAGE = "GENERAL-CHANNEL";
    public static void main(String[] args) {
        new Thread(() -> {
            Gson gson = new Gson();
            Properties props = new Properties();
            props.put("bootstrap.servers", System.getProperty("kafka","193.196.38.196:9092"));
            props.put("group.id", "Consumer-" + UUID.randomUUID());
            props.put("enable.auto.commit", "true");
            props.put("auto.commit.interval.ms", "1000");
            props.put("session.timeout.ms", "30000");
            props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

            consumer = new KafkaConsumer<>(props);
            consumer.subscribe(Arrays.asList(GENERAL_MESSAGE));
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(100);
                for (ConsumerRecord<String, String> record : records){
                    try{
                        RESTcontroller.QRMessage message = gson.fromJson(record.value(), RESTcontroller.QRMessage.class);

                        if(message.getTeam() != null){
                            RESTcontroller.updateQRCode(message);
                            System.out.println("Zuletzt erhaltene Nachricht: \n" + record.value() + "\n");
                        }

                    }catch (Exception e){
                        System.out.println("Nachricht konnte nicht serialisiert werden!");
                    }

                }
            }}).start();
        SpringApplication.run(Starter.class, args);
    }
}

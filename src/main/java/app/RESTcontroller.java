package app;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.web.bind.annotation.*;


@RestController
public class RESTcontroller {

    public static HashMap<String, QRMessage> actualMap = new HashMap<>();
    public static QRMessage BestCode = new QRMessage();
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private static final Object mutex = new Object();

    private static QRMessage getBestTeam(String TeamID){


        QRMessage result;
        synchronized (mutex){
            if(actualMap.containsKey(TeamID)){
                result = actualMap.get(TeamID);
            }else{
                result = new QRMessage();
            }
        }




        return result;
    }

    private static synchronized  QRMessage getBestQR(){

        QRMessage result;
        synchronized (mutex){
            result = BestCode;
        }


        return result;
    }

    public static void updateQRCode(QRMessage qr){
        synchronized (mutex){
            actualMap.put(qr.Team, qr);
            if(qr.Speed > BestCode.Speed){
                BestCode = qr;
            }
        }
    }
    @CrossOrigin
    @RequestMapping(value = "/getQRCode/{TeamId}", method = RequestMethod.GET)
    public QRMessage teamCode(@PathVariable(value="TeamId") String TeamId) {

        if(TeamId == null){
            return new QRMessage();
        }else{
            return getBestTeam(TeamId);
        }

    }
    @CrossOrigin
    @RequestMapping(value = "/getBestCode/", method = RequestMethod.GET)
    public QRMessage bestCode() {

        return getBestQR();
    }

    public static class QRMessage{
        private String QRCode = "Kein CODE";
        private float Speed = 0;
        private String Team = "Kein TEAM";

        public QRMessage() {
        }

        public String getQRCode() {
            return QRCode;
        }

        public void setQRCode(String QRCode) {
            this.QRCode = QRCode;
        }

        public float getSpeed() {
            return Speed;
        }

        public void setSpeed(float speed) {
            Speed = speed;
        }

        public String getTeam() {
            return Team;
        }

        public void setTeam(String team) {
            Team = team;
        }
    }

}

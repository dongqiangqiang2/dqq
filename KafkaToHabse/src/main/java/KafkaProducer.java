import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;

public class KafkaProducer {
    public static void main(String[] args){
        Properties props=new Properties();
        props.put("zk.connect",KafkaProperties.zkConnect);
        props.put("serializer.class","kafka.serializer.StringEncoder");
        props.put("key.serializer.class", "kafka.serializer.StringEncoder");
        props.put("metadata.broker.list","CM-EL67-0001:9092,Tools-EL67-0001:9092");
        props.put("advertised.host.name","CM-EL67-0001");
        ProducerConfig config=new ProducerConfig(props);
        Producer<String,String> producer=new Producer<String,String>(config);
        for(int i=10;i<25;i++){
            //producer.send(new KeyedMessage<String, String>("test2",null,0,"eeeeeeeeeeeeeeeeeeeeeeeeeeee"+i));
           producer.send(new KeyedMessage<String, String>("test2",String.valueOf(i),"eeeeeeeeeeeeeeeeeeeeeeeeeeee"+i));
            //producer.send(new KeyedMessage<String, String>("test3",String.valueOf(i),"eeeeeeeeeeeeeeeeeeeeeeeeeeee"+i));
        }
    }
}

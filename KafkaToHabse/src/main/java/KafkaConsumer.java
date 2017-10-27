import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class KafkaConsumer extends Thread {
    private final ConsumerConnector consumer = kafka.consumer.Consumer
            .createJavaConsumerConnector(createConsumerConfig());
    private final String topic;

    public KafkaConsumer(String topic) {
        this.topic = topic;
    }

    private static ConsumerConfig createConsumerConfig() {
        Properties props = new Properties();
        props.put("zookeeper.connect", KafkaProperties.zkConnect);
        props.put("group.id", KafkaProperties.groupId1);
        props.put("group.name","1");
        props.put("zookeeper.session.timeout.ms", "6000");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "500");
        props.put("rebalance.backoff.ms","2000");
        props.put("rebalance.max.retries","5");
        //props.put("advertised.host.name","CM-EL67-0001");
        //props.put("clientPath","/consumers/cloudera_mirrormaker/ids/cloudera_mirrormaker_Tools-EL67-0001-1508923739107-5a4006f9");
      //  props.put("serverPath","/consumers/cloudera_mirrormaker/ids/cloudera_mirrormaker_Tools-EL67-0001-1508923739107-5a4006f9");
        return new ConsumerConfig(props);
    }

    @Override
    public void run() {
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic, new Integer(1));
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer
                .createMessageStreams(topicCountMap);
        KafkaStream<byte[], byte[]> stream = consumerMap.get(topic).get(0);
        ConsumerIterator<byte[], byte[]> it = stream.iterator();
        HBaseUtils hbase = new HBaseUtils();
        while (it.hasNext()) {
            //System.out.println("3receive：" + new String(it.next().message()));
            try {
                hbase.put(new String(it.next().message()));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

              try {
                  sleep(3000);    // 每条消息延迟300ms
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
        }
    }
}



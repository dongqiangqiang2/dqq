public class KafkaConsumerProducerTest {
    public static void main(String[] args) {

        KafkaConsumer consumerThread = new KafkaConsumer(KafkaProperties.topic);
        consumerThread.start();

    }
}


public class KafkaConsumerProducerTest3 {
    public static void main(String[] args) {

        KafkaConsumer3 consumerThread3 = new KafkaConsumer3(KafkaProperties.topic);
        consumerThread3.start();

    }
}


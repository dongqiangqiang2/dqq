public class KafkaConsumerProducerTest2 {
    public static void main(String[] args) {

        KafkaConsumer2 consumerThread2 = new KafkaConsumer2(KafkaProperties.topic);
        consumerThread2.start();

    }
}


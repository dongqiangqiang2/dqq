public interface KafkaProperties {
    final static String zkConnect = "CM-EL67-0001:2181,Tools-EL67-0001:9092";
    final static String groupId1= "cloudera_mirrormaker";
    final static String topic = "test2";
    //final static String topic = "test3";
    final static String kafkaServerURL = "CM-EL67-0001,Tools-EL67-0001";
    final static int kafkaServerPort = 9092;
    final static int kafkaProducerBufferSize = 64 * 1024;
    final static int connectionTimeOut = 20000;
    final static int reconnectInterval = 10000;
    final static String clientId = "console-producer";
}

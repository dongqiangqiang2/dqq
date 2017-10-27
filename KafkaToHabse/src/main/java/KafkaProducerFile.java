import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class KafkaProducerFile {
    public static void main(String[] args){
        KafkaProducerFile.readFileByLines("filename");

    }

    public static void readFileByLines(String fileName){

        Properties props=new Properties();
        props.put("zk.connect",KafkaProperties.zkConnect);
        props.put("serializer.class","kafka.serializer.StringEncoder");
        props.put("key.serializer.class", "kafka.serializer.StringEncoder");
        props.put("metadata.broker.list","CM-EL67-0001:9092,Tools-EL67-0001:9092");
        props.put("advertised.host.name","CM-EL67-0001");
        ProducerConfig config=new ProducerConfig(props);
        Producer<String,String> producer=new Producer<String,String>(config);

        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            //一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null){
                //显示行号
                String[] strs=tempString.split(",");
                for(int i=0;i<strs.length;i++){
                    producer.send(new KeyedMessage<String, String>("test2",String.valueOf(i),strs[i]));
                }
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }
}

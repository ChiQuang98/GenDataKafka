package threadpool.natsthreadpool;

import KafkaUtils.Utils;
import org.apache.kafka.clients.producer.Producer;

import java.util.List;

public class ThreadWorkerNats implements Runnable {
    private String topicNats;
    private List<String> listDataNats;
    private Producer<String,String> producer;

    public ThreadWorkerNats(String topicNats, List<String> listDataNats, Producer<String, String> producer) {
        this.topicNats = topicNats;
        this.listDataNats = listDataNats;
        this.producer = producer;
    }

    @Override
    public void run() {
        Utils.getInstance().publishMessagesToTopic(topicNats,listDataNats,producer);
    }
}

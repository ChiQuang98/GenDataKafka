package threadpool.mdothreadpool;

import KafkaUtils.Utils;
import org.apache.kafka.clients.producer.Producer;

import java.util.List;

public class ThreadWorkerMDO implements Runnable {
    private String topicMDO;
    private List<String> listDataMDO;
    private Producer<String,String> producer;

    public ThreadWorkerMDO(String topicMDO, List<String> listDataMDO, Producer<String, String> producer) {
        this.topicMDO = topicMDO;
        this.listDataMDO = listDataMDO;
        this.producer = producer;
    }

    @Override
    public void run() {
        Utils.getInstance().publishMessagesToTopic(topicMDO,listDataMDO,producer);
    }
}

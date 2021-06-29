package KafkaUtils;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import threadpool.mdothreadpool.CachedThreadPoolMDO;
import threadpool.mdothreadpool.ThreadWorkerMDO;
import threadpool.natsthreadpool.CachedThreadPoolNats;
import threadpool.natsthreadpool.ThreadWorkerNats;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

public class Utils {
    private static Utils instance;
    private int levelLine2 = 2000;
    private int levelLine4 = 4000;
    private int levelLine6 = 6000;
    private int levelLine8 = 8000;
    private int levelLine10 = 10000;

    public static Utils getInstance() {
        if (instance == null) {
            return new Utils();
        }
        return instance;
    }

    public void publishMessage(String topic, String value, Producer<String, String> producer) {
        String key = UUID.randomUUID().toString();
        producer.send(new ProducerRecord<String, String>(topic, key, value));
    }

    public void processingMessageMDO(ObjectInputStream os, Producer<String, String> producer) {
        CachedThreadPoolMDO threadpoolMDO = CachedThreadPoolMDO.getInstance();
        ExecutorService executorService = threadpoolMDO.getExecutorService();
        String topicMDO = "MDOTOPIC";
        try {
            while (true) {
                try {
                    String data = (String) os.readObject();
                    ;
                    long start = System.currentTimeMillis();
                    List<String> arrData = Arrays.asList(data.split("\n"));
                    int len = arrData.size();
                    if (len <= levelLine2) {
                        ThreadWorkerMDO threadWorkerMDO = new ThreadWorkerMDO(topicMDO, arrData, producer);
                        executorService.execute(threadWorkerMDO);
                    } else if (len <= levelLine4) {
                        int pivot = len / 2;
                        List<String> dataSplit1 = arrData.subList(0, pivot);
                        List<String> dataSplit2 = arrData.subList(pivot, len);
                        ThreadWorkerMDO threadWorkerMDO1 = new ThreadWorkerMDO(topicMDO, dataSplit1, producer);
                        ThreadWorkerMDO threadWorkerMDO2 = new ThreadWorkerMDO(topicMDO, dataSplit2, producer);
                        executorService.execute(threadWorkerMDO1);
                        executorService.execute(threadWorkerMDO2);
                    } else if (len <= levelLine6) {
                        int pivot1 = len / 4;
                        int pivot2 = pivot1 * 2;
                        int pivot3 = pivot1 * 3;
                        List<String> dataSplit1 = arrData.subList(0, pivot1);
                        List<String> dataSplit2 = arrData.subList(pivot1, pivot2);
                        List<String> dataSplit3 = arrData.subList(pivot2, pivot3);
                        List<String> dataSplit4 = arrData.subList(pivot3, len);
                        ThreadWorkerMDO threadWorkerMDO1 = new ThreadWorkerMDO(topicMDO, dataSplit1, producer);
                        ThreadWorkerMDO threadWorkerMDO2 = new ThreadWorkerMDO(topicMDO, dataSplit2, producer);
                        ThreadWorkerMDO threadWorkerMDO3 = new ThreadWorkerMDO(topicMDO, dataSplit3, producer);
                        ThreadWorkerMDO threadWorkerMDO4 = new ThreadWorkerMDO(topicMDO, dataSplit4, producer);
                        executorService.execute(threadWorkerMDO1);
                        executorService.execute(threadWorkerMDO2);
                        executorService.execute(threadWorkerMDO3);
                        executorService.execute(threadWorkerMDO4);
                    } else if (len <= levelLine8) {
                        int pivot1 = len / 5;
                        int pivot2 = pivot1 * 2;
                        int pivot3 = pivot1 * 3;
                        int pivot4 = pivot1 * 4;
                        List<String> dataSplit1 = arrData.subList(0, pivot1);
                        List<String> dataSplit2 = arrData.subList(pivot1, pivot2);
                        List<String> dataSplit3 = arrData.subList(pivot2, pivot3);
                        List<String> dataSplit4 = arrData.subList(pivot3, pivot4);
                        List<String> dataSplit5 = arrData.subList(pivot3, len);
                        ThreadWorkerMDO threadWorkerMDO1 = new ThreadWorkerMDO(topicMDO, dataSplit1, producer);
                        ThreadWorkerMDO threadWorkerMDO2 = new ThreadWorkerMDO(topicMDO, dataSplit2, producer);
                        ThreadWorkerMDO threadWorkerMDO3 = new ThreadWorkerMDO(topicMDO, dataSplit3, producer);
                        ThreadWorkerMDO threadWorkerMDO4 = new ThreadWorkerMDO(topicMDO, dataSplit4, producer);
                        ThreadWorkerMDO threadWorkerMDO5 = new ThreadWorkerMDO(topicMDO, dataSplit5, producer);
                        executorService.execute(threadWorkerMDO1);
                        executorService.execute(threadWorkerMDO2);
                        executorService.execute(threadWorkerMDO3);
                        executorService.execute(threadWorkerMDO4);
                        executorService.execute(threadWorkerMDO5);
                    } else {
                        int pivot1 = len / 6;
                        int pivot2 = pivot1 * 2;
                        int pivot3 = pivot1 * 3;
                        int pivot4 = pivot1 * 4;
                        int pivot5 = pivot1 * 5;
                        List<String> dataSplit1 = arrData.subList(0, pivot1);
                        List<String> dataSplit2 = arrData.subList(pivot1, pivot2);
                        List<String> dataSplit3 = arrData.subList(pivot2, pivot3);
                        List<String> dataSplit4 = arrData.subList(pivot3, pivot4);
                        List<String> dataSplit5 = arrData.subList(pivot4, pivot5);
                        List<String> dataSplit6 = arrData.subList(pivot5, len);
                        ThreadWorkerMDO threadWorkerMDO1 = new ThreadWorkerMDO(topicMDO, dataSplit1, producer);
                        ThreadWorkerMDO threadWorkerMDO2 = new ThreadWorkerMDO(topicMDO, dataSplit2, producer);
                        ThreadWorkerMDO threadWorkerMDO3 = new ThreadWorkerMDO(topicMDO, dataSplit3, producer);
                        ThreadWorkerMDO threadWorkerMDO4 = new ThreadWorkerMDO(topicMDO, dataSplit4, producer);
                        ThreadWorkerMDO threadWorkerMDO5 = new ThreadWorkerMDO(topicMDO, dataSplit5, producer);
                        ThreadWorkerMDO threadWorkerMDO6 = new ThreadWorkerMDO(topicMDO, dataSplit6, producer);
                        executorService.execute(threadWorkerMDO1);
                        executorService.execute(threadWorkerMDO2);
                        executorService.execute(threadWorkerMDO3);
                        executorService.execute(threadWorkerMDO4);
                        executorService.execute(threadWorkerMDO5);
                        executorService.execute(threadWorkerMDO6);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
//                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void publishMessagesToTopic(String topic, List<String> dataList, Producer<String, String> producer) {
        int len = dataList.size();
        for (int i = 0; i < len; i++) {
            Utils.getInstance().publishMessage(topic, dataList.get(i), producer);
        }
    }
    public void processingMessageNats(ObjectInputStream os, Producer<String, String> producer) {
        CachedThreadPoolNats threadpoolNats = CachedThreadPoolNats.getInstance();
        ExecutorService executorService = threadpoolNats.getExecutorService();
        String topicSYS = "SYS_TOPIC";
        System.out.println(topicSYS);
        try {
            while (true) {
                try {
                    String data = (String) os.readObject();
                    List<String> arrData = Arrays.asList(data.split("\n"));
                    int len = arrData.size();
                    if(len<=levelLine2){
                        ThreadWorkerNats threadWorkerNats = new ThreadWorkerNats(topicSYS,arrData,producer);
                        executorService.execute(threadWorkerNats);
                    } else if(len <= levelLine4){
                        int pivot = len/2;
                        List<String>  dataSplit1 = arrData.subList(0,pivot);
                        List<String>  dataSplit2 = arrData.subList(pivot,len);
                        ThreadWorkerNats threadWorkerNats1 = new ThreadWorkerNats(topicSYS,dataSplit1,producer);
                        ThreadWorkerNats threadWorkerNats2 = new ThreadWorkerNats(topicSYS,dataSplit2,producer);
                        executorService.execute(threadWorkerNats1);
                        executorService.execute(threadWorkerNats2);
                    } else if(len<=levelLine6){
                        int pivot1 = len/4;
                        int pivot2 = pivot1 * 2;
                        int pivot3 = pivot1*3;
                        List<String>  dataSplit1 = arrData.subList(0,pivot1);
                        List<String>  dataSplit2 = arrData.subList(pivot1,pivot2);
                        List<String> dataSplit3 = arrData.subList(pivot2,pivot3);
                        List<String> dataSplit4 = arrData.subList(pivot3,len);
                        ThreadWorkerNats threadWorkerNats1 = new ThreadWorkerNats(topicSYS,dataSplit1,producer);
                        ThreadWorkerNats threadWorkerNats2 = new ThreadWorkerNats(topicSYS,dataSplit2,producer);
                        ThreadWorkerNats threadWorkerNats3 = new ThreadWorkerNats(topicSYS,dataSplit3,producer);
                        ThreadWorkerNats threadWorkerNats4 = new ThreadWorkerNats(topicSYS,dataSplit4,producer);
                        executorService.execute(threadWorkerNats1);
                        executorService.execute(threadWorkerNats2);
                        executorService.execute(threadWorkerNats3);
                        executorService.execute(threadWorkerNats4);
                    } else if( len<= levelLine8){
                        int pivot1 = len/5;
                        int pivot2 = pivot1 * 2;
                        int pivot3 = pivot1*3;
                        int pivot4 = pivot1*4;
                        List<String>  dataSplit1 = arrData.subList(0,pivot1);
                        List<String>  dataSplit2 = arrData.subList(pivot1,pivot2);
                        List<String> dataSplit3 = arrData.subList(pivot2,pivot3);
                        List<String> dataSplit4 = arrData.subList(pivot3,pivot4);
                        List<String> dataSplit5 = arrData.subList(pivot3,len);
                        ThreadWorkerNats threadWorkerNats1 = new ThreadWorkerNats(topicSYS,dataSplit1,producer);
                        ThreadWorkerNats threadWorkerNats2 = new ThreadWorkerNats(topicSYS,dataSplit2,producer);
                        ThreadWorkerNats threadWorkerNats3 = new ThreadWorkerNats(topicSYS,dataSplit3,producer);
                        ThreadWorkerNats threadWorkerNats4 = new ThreadWorkerNats(topicSYS,dataSplit4,producer);
                        ThreadWorkerNats threadWorkerNats5 = new ThreadWorkerNats(topicSYS,dataSplit5,producer);
                        executorService.execute(threadWorkerNats1);
                        executorService.execute(threadWorkerNats2);
                        executorService.execute(threadWorkerNats3);
                        executorService.execute(threadWorkerNats4);
                        executorService.execute(threadWorkerNats5);
                    } else {
                        System.out.println("INNNNNNN");
                        int pivot1 = len/6;
                        int pivot2 = pivot1 * 2;
                        int pivot3 = pivot1*3;
                        int pivot4 = pivot1*4;
                        int pivot5 = pivot1*5;
                        List<String>  dataSplit1 = arrData.subList(0,pivot1);
                        List<String>  dataSplit2 = arrData.subList(pivot1,pivot2);
                        List<String> dataSplit3 = arrData.subList(pivot2,pivot3);
                        List<String> dataSplit4 = arrData.subList(pivot3,pivot4);
                        List<String> dataSplit5 = arrData.subList(pivot4,pivot5);
                        List<String> dataSplit6 = arrData.subList(pivot5,len);
                        ThreadWorkerNats threadWorkerNats1 = new ThreadWorkerNats(topicSYS,dataSplit1,producer);
                        ThreadWorkerNats threadWorkerNats2 = new ThreadWorkerNats(topicSYS,dataSplit2,producer);
                        ThreadWorkerNats threadWorkerNats3 = new ThreadWorkerNats(topicSYS,dataSplit3,producer);
                        ThreadWorkerNats threadWorkerNats4 = new ThreadWorkerNats(topicSYS,dataSplit4,producer);
                        ThreadWorkerNats threadWorkerNats5 = new ThreadWorkerNats(topicSYS,dataSplit5,producer);
                        ThreadWorkerNats threadWorkerNats6 = new ThreadWorkerNats(topicSYS,dataSplit6,producer);
                        executorService.execute(threadWorkerNats1);
                        executorService.execute(threadWorkerNats2);
                        executorService.execute(threadWorkerNats3);
                        executorService.execute(threadWorkerNats4);
                        executorService.execute(threadWorkerNats5);
                        executorService.execute(threadWorkerNats6);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

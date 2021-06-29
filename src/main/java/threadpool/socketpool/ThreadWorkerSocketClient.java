package threadpool.socketpool;

import KafkaUtils.KafkaConfig;
import KafkaUtils.Utils;
import org.apache.kafka.clients.producer.Producer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ThreadWorkerSocketClient implements Runnable{
    private Socket socket;
    private int port;

    public ThreadWorkerSocketClient(Socket socket, int port) {
        this.socket = socket;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            Producer<String,String> producer = KafkaConfig.getInstance().createProducer();
            BufferedReader os = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ObjectInputStream ois;
            ois = new ObjectInputStream(socket.getInputStream());
            if(port==11000){
                Utils.getInstance().processingMessageMDO(ois,producer);
            } else{
                Utils.getInstance().processingMessageNats(ois,producer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

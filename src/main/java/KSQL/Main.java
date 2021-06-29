package KSQL;
import KafkaUtils.TCPSocketServer;
import io.confluent.ksql.api.client.Client;

import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        new Thread(new Runnable() {
            public void run() {
                try {
                    final TCPSocketServer server1 = new TCPSocketServer(11000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();


        new Thread(new Runnable() {
            public void run() {
                try {
                    final TCPSocketServer server2 = new TCPSocketServer(11001);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
        Client client = KSQLUtils.getInstance().getClient();

//        KSQLUtils.getInstance().createStream("S",client);

        KSQLUtils.getInstance().queryMapping(client);
    }
}

package KSQL;

import io.confluent.ksql.api.client.Client;
import io.confluent.ksql.api.client.ClientOptions;
import io.confluent.ksql.api.client.StreamedQueryResult;

import java.util.concurrent.ExecutionException;

public class KSQLUtils {
    private String KSQLDB_SERVER_HOST = "localhost";
    private int KSQLDB_SERVER_HOST_PORT = 8088;
    private static KSQLUtils instance;
    private KSQLUtils(){

    }
    public static KSQLUtils getInstance(){
        if (instance==null){
            instance = new KSQLUtils();
        }
        return instance;
    }
    public Client getClient(){
        ClientOptions options = ClientOptions.create()
                .setHost(KSQLDB_SERVER_HOST)
                .setPort(KSQLDB_SERVER_HOST_PORT);
        return Client.create(options);
    }
    public boolean createStream(String StreamName,Client client) throws ExecutionException, InterruptedException {
        System.out.println("Quang");

//        client.ex
        return true;
    }
    public void queryMapping(Client client){
                client.streamQuery("SELECT TIMESTAMPMDO,TIMESTAMPSYS,SYS_IPPRIVATE,PHONENUMBER FROM SYSPHONE WHERE IPPUBLIC = '253.184.95.154' AND PORTPUBLIC = '7256' EMIT CHANGES;")
                .thenAccept(streamedQueryResult -> {
                    System.out.println("Query has started. Query ID: " + streamedQueryResult.queryID());
                    RowSubscriber subscriber = new RowSubscriber();
                    streamedQueryResult.subscribe(subscriber);
                }).exceptionally(e -> {
            System.out.println("Request failed: " + e);
            return null;
        });
    }
}

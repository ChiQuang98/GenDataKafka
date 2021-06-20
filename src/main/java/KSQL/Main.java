package KSQL;
import io.confluent.ksql.api.client.Client;
import io.confluent.ksql.api.client.ClientOptions;
import io.confluent.ksql.api.client.Row;
import io.confluent.ksql.api.client.StreamedQueryResult;

import java.util.concurrent.ExecutionException;

public class Main {
    public static String KSQLDB_SERVER_HOST = "localhost";
    public static int KSQLDB_SERVER_HOST_PORT = 8088;
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ClientOptions options = ClientOptions.create()
                .setHost(KSQLDB_SERVER_HOST)
                .setPort(KSQLDB_SERVER_HOST_PORT);
        Client client = Client.create(options);

        // Send requests with the client by following the other examples
//        StreamedQueryResult streamedQueryResult = client.streamQuery("SELECT * FROM MDOTOPIC EMIT CHANGES;").get();

        client.streamQuery("SELECT * FROM SYSPHONE WHERE TIMESTAMPSYS >= TIMESTAMPMDO EMIT CHANGES;")
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

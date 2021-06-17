
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class TCPClientView {
    private static Integer numMessOnSecond = 0;

    public static void main(String[] args) throws UnknownHostException {
        try {
            final main.TCPCLientController clientController1 = new main.TCPCLientController(InetAddress.getByName("localhost"), 11000);
            final main.TCPCLientController clientController2 = new main.TCPCLientController(InetAddress.getByName("localhost"), 11001);

            // read data from server 1
            new Thread(() -> {
                try {
                    PrintWriter writer = new PrintWriter("MDO.txt", "UTF-8");
                    while (true) {
                        String data = clientController1.readData();
                        System.out.println(data);
                        writer.println(data);
                        numMessOnSecond++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();

            // read data from server 2
            new Thread(() -> {
                try {
                    PrintWriter writer = new PrintWriter("SYS.txt", "UTF-8");
                    while (true) {
                        String data = clientController2.readData();
                        System.out.println(data);
                        writer.println(data);
                        numMessOnSecond++;

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();

            new Thread(() -> {
                try {

                    while (true) {
                        System.out.println("numMessOnSecond : " + numMessOnSecond);
                        numMessOnSecond = 0;
                        Thread.sleep(1000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

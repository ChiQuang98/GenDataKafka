package GenDataKafka;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import org.apache.kafka.clients.producer.Producer;

/**
 *
 * @author dat.chuthanh
 */
public class TCPServerView {
    public static void main(String[] args) {
        Producer<String,String> producer = Utils.createProducer();
        try {
        new Thread(new Runnable() {
            public void run() {
                TCPServerController server1 = new TCPServerController( 1,producer);
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                TCPServerController server2 = new TCPServerController( 2,producer);

            }
        }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

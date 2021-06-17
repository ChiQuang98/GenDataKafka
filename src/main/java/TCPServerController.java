/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import net.andreinc.mockneat.MockNeat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Ryan
 */
public class TCPServerController {


    public static String ipPrivateMatching = "10.100.14.16";
    private volatile AtomicBoolean lock = new AtomicBoolean(false);

    public TCPServerController( int option) {
        try {
            Gendata(option);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void Gendata(final int option){
        try {

            new Thread(new Runnable() {
                public void run() {
                    try {
                        while (true) {
                            while (lock.getAndSet(true));
                            if (option == 1) {
//                                oos.writeObject(genDataFakeFile1());
                                System.out.println(genDataFakeFile1());
                            } else {
//                                oos.writeObject(genDataFakeFile2());
                                System.out.println(genDataFakeFile2());
                            }
                            lock.set(false);
//                            Thread.sleep(1000);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            new Thread(new Runnable() {
                public void run() {
                    try {
                        while (true) {
                            while (lock.getAndSet(true));
                            if (option == 1) {
//                                oos.writeObject(genDataMatchFile1());
                                System.out.println(genDataMatchFile1());
                            } else {
//                                oos.writeObject(genDataMatchFile2());
                                System.out.println(genDataMatchFile2());
                            }
                            lock.set(false);
//                            Thread.sleep(2000);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            new Thread(new Runnable() {
                public void run() {
                    try {
                        while (true) {
                            resetIpPrivateMatching();
//                            Thread.sleep(2000);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resetIpPrivateMatching() {
        MockNeat mock = MockNeat.threadLocal();
        ipPrivateMatching = mock.ipv4s().val();
    }
    public String genDataFakeFile1() {
        String data = "";
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        String strDate = dateFormat.format(date);
        data += strDate + "|RadiusMessage";

        if (Math.random() < 0.5) {
            data += "|Start";
        } else {
            data += "|Stop";
        }

        Random rand = new Random();
        String phone = "84";
        for (int i = 0; i < 9; i++) {
            phone += rand.nextInt(10);
        }

        data += "|" + phone;

        MockNeat mock = MockNeat.threadLocal();

        String ipv4 = mock.ipv4s().val();
        data += "|" + ipv4;
        return data;
    }


    public String genDataFakeFile2() {
        String data = "NVL01_1";
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        String strDate = dateFormat.format(date);
        data += "," + strDate;

        MockNeat mock = MockNeat.threadLocal();
        Random rand = new Random();
        for (int i = 0; i < 3; i++) {
            String ipv4 = mock.ipv4s().val();
            data += "," + ipv4;
            data += "," + rand.nextInt(10000);
        }
        return data;
    }

    public String genDataMatchFile1() {
        String data = "";
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        String strDate = dateFormat.format(date);
        data += strDate + "|RadiusMessage";

        if (Math.random() < 0.5) {
            data += "|Start";
        } else {
            data += "|Stop";
        }

        Random rand = new Random();
        String phone = "84";
        for (int i = 0; i < 9; i++) {
            phone += rand.nextInt(10);
        }
        System.out.println(phone);
        data += "|" + phone;
        data += "|" + ipPrivateMatching;
//        data = "Match1    "+data;
        return data;
    }

    public String genDataMatchFile2() {
        String data = "NVL01_1";
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        String strDate = dateFormat.format(date);
        data += "," + strDate;
        Random rand = new Random();

        data += "," + ipPrivateMatching;
        data += "," + rand.nextInt(10000);

        MockNeat mock = MockNeat.threadLocal();

        for (int i = 0; i < 2; i++) {
            String ipv4 = mock.ipv4s().val();
            data += "," + ipv4;
            data += "," + rand.nextInt(10000);
        }
//        data = "Match2    "+data;
        return data;
    }


}

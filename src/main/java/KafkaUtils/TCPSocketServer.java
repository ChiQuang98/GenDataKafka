package KafkaUtils;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools , Templates
 * and open the template in the editor.
 */


import threadpool.socketpool.FixedThreadPoolSocket;
import threadpool.socketpool.ThreadWorkerSocketClient;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;


public class TCPSocketServer {
    private static volatile AtomicInteger numMessOnSecond = new AtomicInteger(0);
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private volatile AtomicBoolean lock = new AtomicBoolean(false);


    public TCPSocketServer(int port) {
        while (true) {
            try {
                serverSocket = new ServerSocket(port);
                System.out.println("Server TCP with port : " + port + " is running...");

                listening(port);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void listening(int port) {
        FixedThreadPoolSocket threadpool = FixedThreadPoolSocket.getInstance(5);
        ExecutorService executorService = threadpool.getExecutorService();
        while (true) {
            try {
                clientSocket = serverSocket.accept();
                ThreadWorkerSocketClient threadClientSocket = new ThreadWorkerSocketClient(clientSocket,port);
                executorService.execute(threadClientSocket);
                System.out.println(clientSocket.getInetAddress());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}

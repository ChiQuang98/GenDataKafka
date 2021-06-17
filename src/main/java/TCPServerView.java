/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author dat.chuthanh
 */
public class TCPServerView {
    public static void main(String[] args) {
        try {

        new Thread(new Runnable() {
            public void run() {
                main.TCPServerController server1 = new main.TCPServerController(11000, 1);
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                main.TCPServerController server2 = new main.TCPServerController(11001, 2);

            }
        }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

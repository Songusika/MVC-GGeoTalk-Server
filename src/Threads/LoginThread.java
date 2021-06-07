/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import java.net.*;
import java.io.*;
import Model.*;
import java.util.HashMap;
/**
 *
 * @author Seok17
 */
public class LoginThread extends Thread {
    HashMap<> users;
    ServerSocket loginServer;
    Socket socket;
    ServerInfo loginSvInfo;
    UserAccount user;
    

    public LoginThread() {
        loginSvInfo = ServerInfo.getInstance();
        user = new UserAccount();

    }

    public void run() {
        try {
            loginServer = new ServerSocket(loginSvInfo.loginPort);
            System.out.println("로그인 서버 시작됨");
            while (true) {
                socket = loginServer.accept();
                ServerReceiver thread = new ServerReceiver(socket);
                
            }
        } catch (Exception e) {

        }
    }

    class ServerReceiver extends Thread {

        Socket socket;
        ObjectInputStream ois;
        ObjectOutputStream oos;
        UserAccount user;

        public ServerReceiver(Socket socket) {
            this.socket = socket;
            try {
                ois = new ObjectInputStream(socket.getInputStream());
                oos = new ObjectOutputStream(socket.getOutputStream());
            } catch (Exception e) {
            }
        }

        public void run() {
            try {
                while (ois != null) {

                }
            } catch (Exception e) {
            }
        }
    }

}

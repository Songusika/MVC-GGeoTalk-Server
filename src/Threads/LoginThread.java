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

    HashMap<Integer, ObjectOutputStream> usersMap;
    ServerSocket loginServer;
    Socket socket;
    ServerInfo loginSvInfo;
    UserAccount user;
    int userNum = 0;

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
                System.out.println(++userNum + "번 유저 연결 됨");
                ServerReceiver thread = new ServerReceiver(userNum, socket);
                thread.start();
            }
        } catch (Exception e) {

        }
    }

    public void checkType(UserAccount user) {
        switch (user.getType()) {
            case 0:
               try {
                var userInfo = UserDAO.getInstance().loginCheck(user.getId(), user.getPw());
                if (userInfo.isPresent()) {
                    user.setChk(0, 0);
                } else {
                    user.setChk(0, 1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            case 1:

            case 2:

        }
    }

    public void sendObject(int userNum, UserAccount user) {
        try{
            ObjectOutputStream oos = usersMap.get(userNum);
            oos.writeObject(user);
        }catch(Exception e){
        
        }
    }

    class ServerReceiver extends Thread {

        Socket socket;
        ObjectInputStream ois;
        ObjectOutputStream oos;
        UserAccount user;
        int userNum;

        public ServerReceiver(int userNum, Socket socket) {
            this.userNum = userNum;
            this.socket = socket;
            try {
                ois = new ObjectInputStream(socket.getInputStream());
                oos = new ObjectOutputStream(socket.getOutputStream());
                usersMap.put(userNum, oos);
            } catch (Exception e) {
            }
        }

        public void run() {
            try {
                while (ois != null) {
                    user = (UserAccount) ois.readObject();
                    System.out.println(user.getId()+ user.getPw()+ user.getType());
                    checkType(user);
                    sendObject(userNum, user);
                }
            } catch (Exception e) {
            }
        }
    }

}

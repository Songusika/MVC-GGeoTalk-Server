/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import java.net.*;
import java.io.*;
import Model.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Seok17
 */
public class LoginThread extends Thread {

    HashMap<Integer, ObjectOutputStream> usersMap;
    ServerSocket loginServer;
    Socket socket;
    ServerInfo loginSvInfo;
    int userNum = 0;

    public LoginThread() {
        loginSvInfo = ServerInfo.getInstance();
        usersMap = new HashMap<>();
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
                //  System.out.println("체크타입 메서드");
                Optional<UserAccount> userInfo = UserDAO.getInstance().loginCheck(user.getId(), user.getPw());
                //     System.out.println("유저다오 넘어감");
                if (userInfo.isPresent()) {
                    //   System.out.println("if문까지옴");
                    user.setChk(0, 0);
                    break;
                } else {
                    user.setChk(0, 1);
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            case 1:
                Optional<UserAccount> userInfo;
                try {
                    userInfo = UserDAO.getInstance().findPassword(user.getId(), user.getName());
                    System.out.println(user.getId()+" "+ user.getName()); //무슨 값이 왔는지 확인하는 것은 중요하다
                    if (userInfo.isPresent()) {
                        user.setChk(1, 1); //비번이 있다.
                        user.setPw(userInfo.get().getPw());
                        System.out.println("그런 사람 있습니다.");
                        break;
                    } else {
                        user.setChk(1, 0); //비번이 없다
                        System.out.println("그런 사람 없습니다.");
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            case 2:

        }
    }

    public void sendObject(int userNum, UserAccount user) {
        try {
            System.out.println("샌드옵젝까지옴");
            ObjectOutputStream oos = usersMap.get(userNum);

            oos.writeObject(user);
        } catch (Exception e) {
            System.out.println("아잇 시팔");
        }
    }

    public void sendObject2(UserAccount user) {
        try {
            Iterator it = usersMap.keySet().iterator();
            //   System.out.println("샌드옵젝까지옴");
            while (it.hasNext()) {
                ObjectOutputStream oos = (ObjectOutputStream) usersMap.get(it.next());
                oos.writeObject(user);
                System.out.println("WRITE 함");
            }
        } catch (Exception e) {
            System.out.println("아잇 시팔");
        }
    }

    class ServerReceiver extends Thread {

        Socket socket;
        ObjectInputStream ois;
        ObjectOutputStream oos;
        UserAccount user;
        int userNum;

        public ServerReceiver(int userNum, Socket socket) {
            System.out.println(userNum);
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
                    System.out.println("스레드시작");
                    user = (UserAccount) ois.readObject();
                    System.out.println("객체바듬");
                    checkType(user);
                    System.out.println("체크 끝남");
                    sendObject(userNum, user);
                    System.out.println("센드 끝남");
                }
            } catch (Exception e) {
            }
        }
    }

}

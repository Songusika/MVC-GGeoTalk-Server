/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import Model.MessageInfo;
import Model.MessageList;
import Model.UserAccount;
import Model.RoomInfo;
import Model.MessageList;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Seok17
 */
public class ChatThread extends Thread {

    MessageList msgList;
    HashMap<Integer, ObjectOutputStream> usersMap;
    ServerSocket lobbyServer;
    Socket socket;
    ServerInfo sInfo;
    int userNum = 0;
    ObjectInputStream ois;
    ObjectOutputStream oos;

    public ChatThread() {
        sInfo = ServerInfo.getInstance();
        usersMap = new HashMap<>();
        msgList = new MessageList();
        Collections.synchronizedMap(usersMap);
    }

    public void run() {
        try {
            System.out.println("채팅 서버 시작");
            lobbyServer = new ServerSocket(sInfo.chatPort);
            while (true) {
                socket = lobbyServer.accept();

                System.out.println("===================================");
                System.out.println(userNum++ + "번 유저 채팅방 연결됨");

                ChatThread.ServerReceiver thread = new ChatThread.ServerReceiver(userNum, socket);
                thread.start();
            }
        } catch (Exception e) {

        }

    }

    class ServerReceiver extends Thread {

        int Num;
        Socket socket;

        ObjectInputStream ois;
        ObjectOutputStream oos;

        MessageInfo msgObject;
        RoomInfo roominfo;

        public ServerReceiver(int userNum, Socket socket) {
            msgObject = new MessageInfo();
            this.socket = socket;
            this.Num = userNum;
            try {
                this.ois = new ObjectInputStream(socket.getInputStream());
                this.oos = new ObjectOutputStream(socket.getOutputStream());
                usersMap.put(userNum, oos);
            } catch (Exception e) {
            }

        }

        public void run() {
            try {
                while (socket != null) {
                    msgObject = (MessageInfo) ois.readObject();
                    System.out.println("" + msgObject.getId() + "번 방" + msgObject.getName() + " 유저 <" + msgObject.getMessage() + ">  메시지 받음!");
                    msgList.addMessageist(msgObject);
                    System.out.println("메시지 브로드 캐스트 시작!");
                    sendToAll(msgObject);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void sendToAll(MessageInfo msg) {
            Iterator it = usersMap.keySet().iterator();
            while (it.hasNext()) {
                try {
                    
                    oos = usersMap.get(it.next());
                    oos.writeObject(msg);
                    oos.flush();
                    oos.reset();
                    System.out.println("전송되였음!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
           // oos = usersMap.get(Num); //다시 자신의 oos 연결!
        }
    }
}

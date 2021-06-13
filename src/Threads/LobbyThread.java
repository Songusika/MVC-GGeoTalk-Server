/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import Model.FriendDAO;
import Model.FriendInfo;
import Model.FriendList;
import Model.RoomDAO;
import Model.RoomInfo;
import Model.RoomList;
import Model.UserAccount;
import Model.UserDAO;
import Model.LobbyModel;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Optional;

/**
 *
 * @author Seok17
 */
public class LobbyThread extends Thread {
    
    HashMap<Integer, ObjectOutputStream> usersMap;
    ServerSocket lobbyServer;
    Socket socket;
    ServerInfo lobbySvInfo;
    int userNum = 0;
    
    public LobbyThread() {
        lobbySvInfo = ServerInfo.getInstance();
        usersMap = new HashMap<>();
        Collections.synchronizedMap(usersMap);
    }
    
    public void run() {
        try {
            System.out.println("로비 서버 시작");
            lobbyServer = new ServerSocket(lobbySvInfo.lobbyPort);
            while (true) {
                socket = lobbyServer.accept();
                System.out.println("====================================");
                System.out.println(++userNum + "번 유저 로비 서버 연결됨");
                ServerReceiver thread = new ServerReceiver(userNum, socket);
                thread.start();
            }
        } catch (Exception e) {
            
        }
    }
    
    class ServerReceiver extends Thread {
        
        Socket socket;
        ObjectInputStream ois;
        ObjectOutputStream oos;
        
        LobbyModel lobbymodel;
        FriendList friendlist;
        RoomList roomlist;
        RoomInfo roominfo;
        FriendInfo friendinfo;
        
        int userNum;
        
        public ServerReceiver(int userNum, Socket socket) {
            System.out.println(userNum);
            this.userNum = userNum;
            this.socket = socket;
            try {
                this.ois = new ObjectInputStream(socket.getInputStream());
                this.oos = new ObjectOutputStream(socket.getOutputStream());
                usersMap.put(userNum, oos);
            } catch (Exception e) {
            }
        }
        
        public void checkType(LobbyModel lobbymodel) {
            switch (lobbymodel.getType()) {
                case 0: //친구 리스트 요청 
                    System.out.println("친구리스트 요청 받음");
                    try {
                        friendlist = (FriendList) lobbymodel.getModel(0);
                        friendlist = FriendDAO.getInstance().getFriend(friendlist.getMe());
                        lobbymodel.setModel(0, friendlist);
                    } catch (Exception e) {
                        System.out.println("친구 리스트 db 조회 중 익셉션");
                    }
                    break;
                case 1: //방 리스트 요청
                    System.out.println("방리스트 요청 받음");
                    try {
                        roomlist = RoomDAO.getInstance().getRoomlist();
                        lobbymodel.setModel(1, roomlist);
                    } catch (Exception e) {
                        System.out.println("방 리스트 db 조회 중 익셉션");
                    }
                    break;
                case 2: //친구검색 요청
                    System.out.println("친구 검색 요청 받음");
                    try {
                        friendinfo = (FriendInfo) lobbymodel.getModel(2);
                        String me = friendinfo.getUser();
                        String you = friendinfo.getId();
                        int ok = FriendDAO.getInstance().InsertFriend(me, you);
                        friendinfo.setResult(ok);
                        System.out.println("@@@@@@@@@@@@@@@@@@@@@");
                        System.out.println(friendinfo.getResult());
                        lobbymodel.setModel(2, friendinfo);
                        
                    } catch (Exception e) {
                        System.out.println("친구 db 추가 중 익셉션 ");
                    }
                    break;
                case 3: //방 등록 요청 
                    System.out.println("방 등록 요청 받음");
                    try {
                        roominfo = (RoomInfo) lobbymodel.getModel(3);
                        String room = roominfo.getRoomName();
                        int ok = RoomDAO.getInstance().insertRoom(room);
                        roominfo.setType(ok);
                        lobbymodel.setModel(3, roominfo);
                        
                    } catch (Exception e) {
                        System.out.println("방 db 추가 중 익셉션 ");
                    }
                    break;
            }
            
        }
        
        public void sendObject(int userNum, Object receviedObject) {
            try {
                System.out.println("샌드옵젝까지옴");
                ObjectOutputStream oos = usersMap.get(userNum);
                
                oos.writeObject(receviedObject);
                oos.flush();
                oos.reset();
            } catch (Exception e) {
                System.out.println("아잇 시팔");
            }
        }
        
        public void run() {
            try {
                while (ois != null) {
                    lobbymodel = (LobbyModel) ois.readObject();
                    System.out.println("받기 성공");
                    checkType(lobbymodel);
                    
                    sendObject(userNum, lobbymodel);
                    System.out.println("센드 끝남");
                    lobbymodel = null;
                }
            } catch (Exception e) {
            }
        }
    }
}

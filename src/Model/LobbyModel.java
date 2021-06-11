/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author Seok17
 */
public class LobbyModel implements Serializable{

    FriendList friendlist;
    RoomList roomlist;
    RoomInfo roominfo;
    FriendInfo friendinfo;

    int type; //0 = 친구 리스트 요청, 1 = 방 리스트 요청, 2 = 친구검색 요청, 3 = 방 등록 요청

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setModel(int type, Object model) {  //클라이언트와 서버가 사용하는 메서드
        switch (type) {
            case 0:   
                friendlist = (FriendList) model;
                break;
            case 1:
                roomlist = (RoomList)model;
                break;
            case 2:
                friendinfo = (FriendInfo)model;
                break;
            case 3:
                roominfo = (RoomInfo)model;
                break;
        }
    }
    
    public Object getModel(int type){
        switch(type){
            case 0:
                return friendlist;
            case 1:
                return roomlist;
            case 2:
                return friendinfo;
            case 3:
                return roominfo;
        }
        return null;
    }
}

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
public class RoomInfo implements Serializable{
     private String roomName;
    private int type;
    private int roomid;
    
    public void setRoomName(String roomName){
        this.roomName = roomName;
    }
    public void setType(int roomId){
        this.type = type;
    }
    public String getRoomName(){
        return this.roomName;
    }
    public int getType(){
        return this.type;
    }
    
    public void setroomId(int id){
        this.roomid = id;
    }
    public int getroomId(){
        return this.roomid;
    }
}

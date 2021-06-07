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
    private int roomId;
    
    public void setRoomName(String roomName){
        this.roomName = roomName;
    }
    public void setroomId(int roomId){
        this.roomId = roomId;
    }
    public String getRoomName(){
        return this.roomName;
    }
    public int getroomId(){
        return this.roomId;
    }
}

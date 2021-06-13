/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Seok17
 */
public class RoomList implements Serializable {
    
    private static final long serialVersionUID = 6529685098267757690L;

    private ArrayList<RoomInfo> roomlist;
    int len=0;
    
    public RoomList(){
        roomlist = new ArrayList<RoomInfo>();
    }
    
    public ArrayList<RoomInfo> getRoomlist() {
        return roomlist;
    }

    public void setRoomlist(ArrayList<RoomInfo> roomlist) {
        this.roomlist = roomlist;
    }

    public void addRoomlist(RoomInfo room) {
        roomlist.add(room);
    }
    
    public void setlen(int len){
        this.len = len;
    }
    
    public int getlen(){
        return len;
    }
    
    public RoomInfo getRoom(int i){
        return roomlist.get(i);
    }
    
    public String[] getRoomNames(){
        Iterator<RoomInfo> it = roomlist.iterator();
        String[] roomnames = new String[this.getlen()];
        int i = 0;
        while(it.hasNext()){
            RoomInfo room = it.next();
            roomnames[i++] = room.getRoomName();
        }
        return roomnames;
        
    }
}

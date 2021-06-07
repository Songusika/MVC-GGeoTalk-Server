/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 * @author Seok17
 */
public class RoomList implements Serializable{
    private ArrayList<RoomInfo> roomlist;

	public ArrayList<RoomInfo> getRoomlist() {
		return roomlist;
	}

	public void setRoomlist(ArrayList<RoomInfo> roomlist) {
		this.roomlist = roomlist;
	}
	public void addRoomlist(RoomInfo room) {
		roomlist.add(room);
	}
}

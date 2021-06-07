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
public class FriendList implements Serializable{
        private ArrayList<FriendInfo> friendlist;

	public ArrayList<FriendInfo> getFriendlist() {
		return friendlist;
	}

	public void setFriendlist(ArrayList<FriendInfo> friendlist) {
		this.friendlist = friendlist;
	}
	public void addFriendlist(FriendInfo friend) {
		friendlist.add(friend);
	}
}

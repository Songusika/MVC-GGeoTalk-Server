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
public class FriendList implements Serializable{
        private UserAccount user;
        private ArrayList<FriendInfo> friendlist;
        private int len = 0;
        
        public FriendList(){
            friendlist = new ArrayList<FriendInfo>();
        }
        
        public FriendList(UserAccount user){
            this.user = user;
            friendlist = new ArrayList<FriendInfo>();
        }
        
	public ArrayList<FriendInfo> getFriendlist() {
		return friendlist;
	}
        
        public void setUser(UserAccount user){
            this.user = user;
        }

	public void setFriendlist(ArrayList<FriendInfo> friendlist) {
		this.friendlist = friendlist;
	}
        
	public void addFriendlist(FriendInfo friend) {
		friendlist.add(friend);
	}
        
        public void setlen(int len){
            this.len = len;
        }
        
        public int getlen(){
            return len; 
        }
        
        public String getMe(){
            String id = user.getId();
            return id;
        }
        public String[] getNames(){
            Iterator<FriendInfo> it = friendlist.iterator();
            String[] names = new String[friendlist.size()];
            int i = 0;
            while(it.hasNext()){
                FriendInfo friend = it.next();
                names[i++] = friend.getName()+"("+friend.getId()+")";
            }
            return names;
        }
      
}

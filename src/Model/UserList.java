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
public class UserList implements Serializable{
        private ArrayList<UserAccount> userList = new ArrayList();
        
	public ArrayList<UserAccount> getUserList() {
		return userList;
	}

	public void setUserlist(ArrayList<UserAccount> UserList) {
		this.userList = UserList;
	}
	public void addUserlist(UserAccount user) {
		userList.add(user);
	}
}

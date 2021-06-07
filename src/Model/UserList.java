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
        private ArrayList<UserAccount> UserList;

	public ArrayList<UserAccount> getUserList() {
		return UserList;
	}

	public void setUserlist(ArrayList<UserAccount> UserList) {
		this.UserList = UserList;
	}
	public void addUserlist(UserAccount user) {
		UserList.add(user);
	}
}

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
public class FriendInfo implements Serializable{
    private String user;
	private String Id;
	private String name;
        private int result = 0; //1성공, 2 없는, 0중복  
	private int type;  //0 추가, 1 삭제 
	
        public FriendInfo(){}
        
        public FriendInfo(String user){
            this.user = user;
        }
        
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
        public void setResult(int result){
            this.result = result;
        }
        
        public int getResult(){
            return this.result;
        }
}

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
public class UserAccount implements Serializable{
    private String id;
    private String pw;
    private String name;
    private int type;  //0 = 로그인, 1 = 회원가입, 2 = 계정 찾기
    private int loginChk; // 로그인 할때, 0 = 로그인 허용, 1 = 로그인 불허
    private int idChk; // 회원가입시 아이디 중복 체크 0 = 없음, 1 = 있음
    private int pwChk; // 비밀번호 찾기, 0 = 맞는 비번 없음, 1 = 있음
    
    public String getId(){
        return id;
    }
    public String getPw(){
        return pw;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setId(String id){
        this.id = id;
    }
    public void setPw(String pw){
        this.pw = pw;
    }
    public int getType() {
	return type;
    }
    public void setType(int type) {
	this.type = type;
    }
    public void setChk(int type, int chk){
        switch(type){
            case 0:
                this.loginChk = chk;
                break;
            case 1:
                this.idChk = chk;
            case 2:
                this.pwChk = chk;
        }
    }

public int getChk(int type){
    switch(type){
                case 0:
                    return this.loginChk;
                case 1:
                    return this.idChk; 
                case 2:
                    return this.pwChk;
                default:
                    return 4;
            }
}

}

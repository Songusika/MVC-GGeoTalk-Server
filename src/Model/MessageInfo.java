/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.io.File;
import java.io.Serializable;
/**
 *
 * @author Seok17
 */
public class MessageInfo implements Serializable {
    private String user;//유저아이디
    private int Id;//방아이디
    private String name;//유저이름
    private int type;//메세지 타입 0= 글자, 1= 이미지
    private String message; //채팅 메세지
    private int imageid; //이미지아이디
    private String time;//채팅이 저장된 시간
    private byte[] file;
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public int getImageid() {
        return imageid;
    }
    public void setImageid(int imageid) {
        this.imageid = imageid;
    }
    public byte[] getFile() {
        return file;
    }
    public void setFile(byte[] file) {
        this.file = file;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public int getId() {
        return Id;
    }
    public void setId(int id) {
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
}
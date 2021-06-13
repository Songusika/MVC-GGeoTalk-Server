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
 * @author 권대철
 */
public class MessageList implements Serializable {

    private ArrayList<MessageInfo> messagelist;
    private int len = 0;

    public ArrayList<MessageInfo> getMessagelist() {
        return messagelist;
    }

    synchronized public void setMessagelist(ArrayList<MessageInfo> messagelist) {
        this.messagelist = messagelist;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

     synchronized public void addMessageist(MessageInfo messageInfo) {
        messagelist.add(messageInfo);
    }

    public MessageList() {
        messagelist = new ArrayList<MessageInfo>();
    }

}

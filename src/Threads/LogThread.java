/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import Model.MessageList;
import Model.MessageInfo;
import java.net.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author Seok17
 */
public class LogThread extends Thread {

    ArrayList<MessageInfo> msglist;
   // ObjectInputStream ois;
    ObjectOutputStream oos;

    public LogThread(ObjectOutputStream oos) {
        
        this.oos = oos;

        this.msglist = ChatThread.msgList.getMessagelist();
    }

    public void sendToAll() {
        try {
            Iterator it = msglist.iterator();

            while (it.hasNext()) {
                oos.writeObject(it.next());
                oos.flush();
                oos.reset();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        sendToAll();
    }
}

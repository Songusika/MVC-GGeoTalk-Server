/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

/**
 *
 * @author Seok17
 */
public class ServerInfo {
    
    public static ServerInfo serverInfo = new ServerInfo();
    String serverIp = "127.0.0.1";
    int loginPort = 9999;
    int lobbyPort = 9998;
    int chatPort = 9997;
    
    public ServerInfo(){
        
    }
    
    public static ServerInfo getInstance(){
        return serverInfo;
    }
}

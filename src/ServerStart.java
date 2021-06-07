/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Threads.*;
/**
 *
 * @author Seok17
 */
public class ServerStart {
    public static void main(String[] args){
        LoginThread login = new LoginThread();
        login.start();
    }
}

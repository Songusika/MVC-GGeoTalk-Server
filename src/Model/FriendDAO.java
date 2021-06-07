/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.awt.EventQueue;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ArrayList;
import java.sql.SQLException;

public class FriendDAO {
    private static FriendDAO friendDao = new FriendDAO();
    private static MariaDBConnector db = MariaDBConnector.getInstance();
    
    Connection con;
    PreparedStatement pstmt;
    ResultSet rs;
    FriendInfo friend;
    FriendList friendList;
    
    private FriendDAO(){
    }
    
    public static FriendDAO getInstance(){
        return friendDao;
    }
    
    //친구 리스트 뽑는 함수
    
    
    
}

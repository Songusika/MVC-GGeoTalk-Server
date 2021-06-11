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

/**
 *
 * @author Seok17
 */
public class RoomDAO {
    private static RoomDAO roomDao = new RoomDAO();
    private static MariaDBConnector db = MariaDBConnector.getInstance();
    
    Connection con;
    PreparedStatement pstmt;
    ResultSet rs;
    RoomInfo roomInfo;
    RoomList roomList;
    
    private RoomDAO(){
    }
    
    public static RoomDAO getInstance(){
        return roomDao;
    } 
    
    public int insertRoom(String roomName){
            con = db.getConnection();
            int RoomOk;
            try{
                int i = 0;
                String sql = "INSERT INTO ROOM VALUES ('"+ i + "','" + roomName + "')";
                pstmt = con.prepareStatement(sql);
                RoomOk = pstmt.executeUpdate();
            }catch(Exception e){
                return RoomOk = 0;
            }
            return RoomOk;
        }
    
    public RoomList getRoomlist() throws Exception{
            con = db.getConnection();
            String sql = "SELECT * FROM ROOM";
            try{
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            roomList = new RoomList();
           
            while (rs.next()) {
			roomInfo = new RoomInfo();
			roomInfo.setroomId(rs.getInt("roomid"));
			roomInfo.setRoomName(rs.getString("roomname"));
			roomList.addRoomlist(roomInfo);
		}
             }catch(Exception e){
                e.printStackTrace();
            }
            roomList.setlen(getRoomLen());
            return roomList;
        }
        
         public int getRoomLen(){
            con = db.getConnection();
		int result=0;
		try {
			String sql = "SELECT count(*) cnt FROM ROOM";
			pstmt = con.prepareStatement(sql);
                        rs = pstmt.executeQuery();
			if(rs.next()){
                            int cnt = rs.getInt("cnt");
                            result = cnt;
                        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
                return result;
         }
}

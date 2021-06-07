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
public class UserDAO {
     private static UserDAO userDao = new UserDAO();
    private static MariaDBConnector db = MariaDBConnector.getInstance();
    
    Connection con;
    PreparedStatement pstmt;
    ResultSet rs;
    UserAccount user;
    UserList userList;
    
    private UserDAO(){
	}

	public static UserDAO getInstance() {
		return userDao;
	}

	public UserList select() throws Exception {
		con = db.getConnection();
		String sql = "SELECT * FROM CUSTOMER";
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		userList = new UserList();
                
		while (rs.next()) {
			user = new UserAccount();
			user.setId(rs.getString("id"));
			user.setPw(rs.getString("pw"));
			user.setName(rs.getString("name"));
			userList.addUserlist(user);
		}
		return userList;
	}

        public boolean check(String id){
            con = db.getConnection();
		ResultSet loginOk;
		try {
			String sql = "SELECT count(*) cnt FROM CUSTOMER WHERE id=?";
			pstmt = con.prepareStatement(sql);
                        pstmt.setString(1, id);
                        rs = pstmt.executeQuery();
			if(rs.next()){
                            int cnt = rs.getInt("cnt");
                            if(cnt > 0){
                                return true;
                            }
                        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
              
		return false;
        }
        
	public int insert(String id, String pw, String name) {
		con = db.getConnection();
		int loginOk;
		try {
			String sql = "INSERT INTO CUSTOMER VALUES ('" + id + "','" + pw + "','" + name + "')";
			pstmt = con.prepareStatement(sql);
			loginOk = pstmt.executeUpdate();
		} catch (Exception e) {
			return loginOk = 0;
		}
		return loginOk;
	}
        
        public Optional<UserAccount> loginCheck(String id, String pw) throws Exception {
		con = db.getConnection();
		UserList userList = this.select();
		//boolean loginOk = false;
		for (UserAccount user : userList.getUserList()) {
			if (id.equals(user.getId()) && pw.equals(user.getPw()))
				return Optional.of(user);
		}
		return Optional.empty();
	}
        public Optional<UserAccount> findPassword(String id, String name) throws Exception {
		con = db.getConnection();
		UserList userList = this.select();
		//boolean loginOk = false;
		for (UserAccount user : userList.getUserList()) {
			if (id.equals(user.getId()) && name.equals(user.getName()))
				return Optional.of(user);
		}
		return Optional.empty();
	}
    
}

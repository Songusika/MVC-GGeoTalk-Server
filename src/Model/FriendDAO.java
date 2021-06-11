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

    private FriendDAO() {
    }

    public static FriendDAO getInstance() {
        return friendDao;
    }

    //친구 리스트 뽑는 함수 인자(나) 유저아이디와 친구아이디, 친구이름 리스트 반환
    public Optional<FriendList> getFriend(String me) throws Exception {
        con = db.getConnection();
        String sql = "SELECT f.user_id, f.friend_id, c.NAME FROM gegeo_db.FRIEND f INNER JOIN gegeo_db.CUSTOMER c ON f.friend_id  = c.id  WHERE f.user_id ='" + me + "'";
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            friendList = new FriendList(); //친구리스트 모델 생성

            while (rs.next()) {
                friend = new FriendInfo();
                friend.setUser(rs.getString("user_id")); //유저 아이디
                friend.setId(rs.getString("friend_id")); //친구 아이디
                friend.setName(rs.getString("NAME")); //친구 이름
                friendList.addFriendlist(friend); //리스트에 추가
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
        for (FriendInfo friend : friendList.getFriendlist()) {
            if (!(me.equals(friend.getUser()))) {
                return Optional.empty(); //하나라도 오류가 있다면 문제가 있다.
            }
        }
        return Optional.of(friendList);//테스트를 통과한 트-루 친구리스트
    }

    //친구 추가함수 인자(나, 추가할 친구) (이미 있는 친구 추가를 막는 기능 추가)
    public int InsertFriend(String me, String you) {
        int FriendOK; //친구 추가 되었는지 숫자로 전달(디비에서 값 머오는지 확인 필요)
        if (friendDuplicateCheck(me, you)) {
            return FriendOK = 0;//실패
        }
        con = db.getConnection();
        try {
            String sql = "INSERT INTO FRIEND(user_id, friend_id) VALUES ('" + me + "','" + you + "')";
            pstmt = con.prepareStatement(sql);
            FriendOK = pstmt.executeUpdate();
        } catch (Exception e) {
            return FriendOK = 0;
        }
        return FriendOK;

    }

    //친구 삭제함수(나, 삭제할 친구)
    public int deleteFriend(String me, String you) {
        con = db.getConnection();
        int BanOK; //너 밴
        try {
            String sql = "DELETE FROM gegeo_db.FRIEND WHERE user_id = '" + me + "'AND friend_id'" + you + "'";
            pstmt = con.prepareStatement(sql);
            BanOK = pstmt.executeUpdate();
        } catch (Exception e) {
            return BanOK = 0;
        }
        return BanOK;
    }

    //친구 검증함수(이미 있나?)
    public boolean friendDuplicateCheck(String me, String you) {
        con = db.getConnection();
        int duplOK;
        try {
            String sql = "SELECT count(*) cnt FROM FRIEND WHERE user_id=? AND friend_id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, me);
            pstmt.setString(2, you);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int cnt = rs.getInt("cnt");
                if (cnt > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}

package com.fastcampus.ch3.dao;

import com.fastcampus.ch3.domain.*;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private SqlSession session;

    private static String namespace = "com.fastcampus.ch3.dao.UserMapper.";

    @Override
    public int count() throws Exception {
        return session.selectOne(namespace+"count");
    }

    @Override
    public int deleteUser(UserDto userDto) throws Exception{
        return session.delete(namespace+"delete");
    }
    @Override
    public int updateUser(UserDto userDto) throws Exception {
        int rowCnt=0;
        String sql="update user_info set pwd=?, name=?, email=?, sns=?, address=?, phone_num=? where userid=?";
        Connection conn = ds.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, userDto.getPwd());
        pstmt.setString(2, userDto.getName());
        pstmt.setString(3, userDto.getEmail());
        pstmt.setString(4, userDto.getSns());
        pstmt.setString(5, userDto.getAddress());
        pstmt.setString(6, userDto.getPhone_num());
        pstmt.setString(7, userDto.getUserId());

        rowCnt = pstmt.executeUpdate();

        return rowCnt;
    }

    @Override
    public UserDto selectUser(String userId) throws Exception {
        UserDto user = null;
        int rowCnt=FAIL;
        String sql = "select * from user_info where userid=?";

        Connection conn = ds.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, userId);

        ResultSet rs = pstmt.executeQuery();

        if(rs.next()){
            user = new UserDto();
            user.setUserId(rs.getString(1));
            user.setPwd(rs.getString(2));
            user.setName(rs.getString(3));
            user.setEmail(rs.getString(4));
            user.setSns(rs.getString(5));
            user.setAddress(rs.getString(6));
            user.setPhone_num(rs.getString(7));
            user.setCreate_date(rs.getDate(8));
        }
        return user;
    }




    @Override
    public int deleteAll() throws Exception{
        int rowCnt;
        String sql = "delete from user_info";

        Connection conn = ds.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        rowCnt = pstmt.executeUpdate();
        return rowCnt;
    }

    @Override
    public int insertUser(UserDto userDto) throws Exception {
        int rowCnt = FAIL;
        String sql = "insert into user_info values (?,?,?,?,?,?,?,now())";

        try {
            Connection conn = ds.getConnection(); // 데이터베이스의 연결을 얻는다.
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, userDto.getUserId());
            pstmt.setString(2, userDto.getPwd());
            pstmt.setString(3, userDto.getName());
            pstmt.setString(4, userDto.getEmail());
            pstmt.setString(5, userDto.getSns());
            pstmt.setString(6, userDto.getAddress());
            pstmt.setString(7, userDto.getPhone_num());

            rowCnt = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return FAIL;
        }
        return rowCnt;
    }


}
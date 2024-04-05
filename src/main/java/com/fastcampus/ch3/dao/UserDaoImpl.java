package com.fastcampus.ch3.dao;

import com.fastcampus.ch3.domain.*;
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
    DataSource ds;

    final int FAIL = 0;

    @Override
    public int updateUser(UserDto userDto) throws SQLException {
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
    public UserDto selectUser(UserDto userDto) throws SQLException {
        int rowCnt=FAIL;
        String sql = "select * from user_info where userid=?";

        Connection conn = ds.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, userDto.getUserId());

        ResultSet rs = pstmt.executeQuery();

        if(rs.next()){
            userDto = new UserDto();
            userDto.setUserId(rs.getString(1));
            userDto.setPwd(rs.getString(2));
            userDto.setName(rs.getString(3));
            userDto.setEmail(rs.getString(4));
            userDto.setSns(rs.getString(5));
            userDto.setAddress(rs.getString(6));
            userDto.setPhone_num(rs.getString(7));
            userDto.setCreate_date(rs.getDate(8));
        }
        return userDto;
    }

    @Override
    public int deleteUser(UserDto userDto) throws SQLException{
        int rowCnt;
        String sql = "delete from user_info where userid= ?";

        Connection conn = ds.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, userDto.getUserId());
        rowCnt = pstmt.executeUpdate();
        return rowCnt;
    }


    @Override
    public int deleteAll() throws SQLException{
        int rowCnt;
        String sql = "delete from user_info";

        Connection conn = ds.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);

        rowCnt = pstmt.executeUpdate();
        return rowCnt;
    }

    @Override
    public int insertUser(UserDto userDto) throws SQLException {
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

    @Override
    public int count() throws SQLException {
        int result=0;
        String sql = "select count(*) from user_info";
        Connection conn = ds.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        if(rs.next()){
            result = rs.getInt(1);
        }
        return result;
    }
}
package com.fastcampus.ch3.dao;

import com.fastcampus.ch3.domain.UserDto;

import java.sql.SQLException;

public interface UserDao {
    int updateUser(UserDto userDto) throws SQLException;

    UserDto selectUser(UserDto userDto) throws SQLException;

    int deleteUser(UserDto userDto) throws SQLException;

    int deleteAll() throws SQLException;

    int insertUser(UserDto userDto) throws SQLException;

    int count() throws SQLException;
}

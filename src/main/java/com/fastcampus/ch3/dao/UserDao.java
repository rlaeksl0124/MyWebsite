package com.fastcampus.ch3.dao;

import com.fastcampus.ch3.domain.UserDto;

import java.sql.SQLException;

public interface UserDao {
//    int updateUser(UserDto userDto) throws Exception;
//
//    UserDto selectUser(String userId) throws Exception;
//
    int deleteUser(UserDto userDto) throws Exception;
//
//    int deleteAll() throws Exception;
//
//    int insertUser(UserDto userDto) throws Exception;

    int count() throws Exception;
}

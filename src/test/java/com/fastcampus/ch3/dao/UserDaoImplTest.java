package com.fastcampus.ch3.dao;

import com.fastcampus.ch3.domain.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class UserDaoImplTest {
    @Autowired
    private UserDao userDao;
    DataSource ds;

    final int FAIL = 0;

    @Test
    public void updateUserTest() throws Exception{
        userDao.deleteAll();
        UserDto user = new UserDto("qwer5", "1234", "dani", "rlaeksl0124", "kakao", "seoul", "010-7741-1550", new Date());
        assertTrue(userDao.insertUser(user)==1);

        user.setPwd("5678");
        user.setName("update name");
        assertTrue(userDao.updateUser(user)==1);
        assertTrue(userDao.count()==1);

        UserDto user2 = userDao.selectUser(user.getUserId());
        assertEquals(user, user2);
    }

    @Test
    public void selectUserTest() throws Exception {
        userDao.deleteAll();
        // insertUser
        UserDto user = new UserDto("qwer5", "1234", "dani", "rlaeksl0124", "kakao", "seoul", "010-7741-1550", new Date());
        assertTrue(userDao.insertUser(user)==1);
        assertTrue(userDao.count()==1);

        // selectUser
        UserDto userDto2 = userDao.selectUser(user.getUserId());
        assertEquals(user, userDto2);
    }

    @Test
    public void deleteUserTest() throws Exception {
        userDao.deleteAll();
        assertTrue(userDao.deleteAll()==0);

        // insertUser
        UserDto user = new UserDto("qwer5", "1234", "dani", "rlaeksl0124", "kakao", "seoul", "010-7741-1550", new Date());
        assertTrue(userDao.insertUser(user)==1);
        assertTrue(userDao.count()==1);

        // insertUser
        UserDto user2 = new UserDto("qwer6", "1234", "dani", "rlaeksl0124", "kakao", "seoul", "010-7741-1550", new Date());
        assertTrue(userDao.insertUser(user2)==1);
        assertTrue(userDao.count()==2);

        //deleteAll
        userDao.deleteAll();
        assertTrue(userDao.deleteAll()==0);
    }

    @Test
    public void deleteAllTest() throws Exception {
        userDao.deleteAll();

        // insertUser
        UserDto user = new UserDto("qwer3", "1234", "dani", "rlaeksl0124", "kakao", "seoul", "010-7741-1550", new Date());
        assertTrue(userDao.insertUser(user)==1);
        assertTrue(userDao.count()==1);

        userDao.deleteUser(user);
        assertTrue(userDao.deleteUser(user)==0);
    }

    @Test
    public void insertUser() throws Exception {
        userDao.deleteAll();
        UserDto user = new UserDto("qwer3", "1234", "dani", "rlaeksl0124", "kakao", "seoul", "010-7741-1550", new Date());
        assertTrue(userDao.insertUser(user)==1);
        assertTrue(userDao.count()==1);
    }

    @Test
    public void count() throws Exception{
        userDao.deleteAll();
        assertTrue(userDao.count()==0);

        UserDto user = new UserDto("qwer3", "1234", "dani", "rlaeksl0124", "kakao", "seoul", "010-7741-1550", new Date());
        assertTrue(userDao.insertUser(user)==1);
        assertTrue(userDao.count()==1);

        UserDto user2 = new UserDto("qwer4", "1234", "dani", "rlaeksl0124", "kakao", "seoul", "010-7741-1550", new Date());
        assertTrue(userDao.insertUser(user2)==1);
        assertTrue(userDao.count()==2);
    }
}
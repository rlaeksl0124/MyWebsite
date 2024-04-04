package com.fastcampus.ch3.dao;

import com.fastcampus.ch3.domain.*;
import org.apache.ibatis.session.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public class BoardDao {
    @Autowired
    private SqlSession  session;
    private static String namespace = "com.fastcampus.ch3.dao.BoardMapper.";
    BoardDto select(int bno) throws Exception{
        return session.selectOne(namespace+"select",bno);
    }
}

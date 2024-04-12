package com.fastcampus.ch3.dao;

import com.fastcampus.ch3.domain.*;
import org.apache.ibatis.session.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public class BoardDaoImpl implements BoardDao {
    @Autowired
    private SqlSession  session;
    private static String namespace = "com.fastcampus.ch3.dao.BoardMapper.";

    @Override
    public int count() throws Exception{
        return session.selectOne(namespace+"count");
    }

    @Override
    public int delete(Integer bno, String writer) throws Exception{
        Map map = new HashMap();
        map.put("bno", bno);
        map.put("writer", writer);
        return session.delete(namespace+"delete", map);
    }

    @Override
    public int deleteAll() throws Exception{
        return session.delete(namespace+"deleteAll");
    }

    @Override
    public int insert(BoardDto board) throws Exception{
        return session.insert(namespace+"insert", board);
    }

    @Override
    public List<BoardDto> selectAll() throws Exception {
        return session.selectList(namespace+"selectAll");
    }

    @Override
    public BoardDto select(Integer bno) throws Exception{
        return session.selectOne(namespace+"select",bno);
    }

    @Override
    public List<BoardDto> selectPage(Map map) throws Exception{
        return session.selectList(namespace+"selectPage",map);
    }

    @Override
    public int update(BoardDto board) throws Exception{
        return session.update(namespace+"update", board);
    }

    @Override
    public int updateCommentCnt(Map map) throws Exception{
        return session.update(namespace+"updateCommentCnt", map);
    }

    @Override
    public int increaseViewCnt(Integer bno) throws Exception{
        return session.update(namespace+"increaseViewCnt", bno);
    }
}

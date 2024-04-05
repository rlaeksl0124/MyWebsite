package com.fastcampus.ch3.dao;

import com.fastcampus.ch3.domain.BoardDto;

import java.util.List;
import java.util.Map;

public interface BoardDao {
    int count() throws Exception;

    int delete(Integer bno, String writer) throws Exception;

    int deleteAll() throws Exception;

    int insert(BoardDto board) throws Exception;

    List<BoardDto> selectAll() throws Exception;

    BoardDto select(int bno) throws Exception;

    List<BoardDto> selectPage(Map map) throws Exception;

    int update(BoardDto board) throws Exception;

    int updateCommentCnt(Map map) throws Exception;

    int increaseViewCnt(Integer bno) throws Exception;
}

package com.fastcampus.ch3.service;

import com.fastcampus.ch3.domain.BoardDto;

import java.util.List;
import java.util.Map;

public interface BoardService {
    int getCount() throws Exception;

    int remove(Integer bno, String writer) throws Exception;

    int write(BoardDto board) throws Exception;

    List<BoardDto> readAll() throws Exception;

    BoardDto read(Integer bno) throws Exception;

    List<BoardDto> getPage(Map map) throws Exception;

    int update(BoardDto board) throws Exception;
}

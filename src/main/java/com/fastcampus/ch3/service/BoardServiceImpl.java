package com.fastcampus.ch3.service;

import com.fastcampus.ch3.dao.BoardDao;
import com.fastcampus.ch3.domain.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BoardServiceImpl implements BoardService {
    @Autowired
    BoardDao boardDao;

    @Override
    public int ser_Count() throws Exception {
        return boardDao.count();
    }

    @Override
    public int remove(Integer bno, String writer) throws Exception {
        return boardDao.delete(bno, writer);
    }

    @Override
    public int write(BoardDto board) throws Exception {
        return boardDao.insert(board);
    }

    @Override
    public List<BoardDto> readAll() throws Exception {
        return boardDao.selectAll();
    }

    @Override
    public BoardDto read(Integer bno) throws Exception {
        return boardDao.select(bno);
    }

    @Override
    public List<BoardDto> ser_getPage(Map map) throws Exception{
        return boardDao.selectPage(map);
    }

    @Override
    public int ser_update(BoardDto board) throws Exception {
        return boardDao.update(board);
    }

}

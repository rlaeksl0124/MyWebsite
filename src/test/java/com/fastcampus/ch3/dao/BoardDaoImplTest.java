package com.fastcampus.ch3.dao;

import com.fastcampus.ch3.domain.BoardDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class BoardDaoImplTest {
    @Autowired
    private BoardDao boardDao;

    @Test
    public void count() throws Exception {
        boardDao.deleteAll();
        assertTrue(boardDao.count()==0);

        BoardDto board = new BoardDto("title1", "content1", "writer1");
        assertTrue(boardDao.insert(board)==1);
        assertTrue(boardDao.count()==1);

        assertTrue(boardDao.insert(board)==1);
        assertTrue(boardDao.count()==2);
    }

    @Test
    public void delete() throws Exception {
        boardDao.deleteAll();
        assertTrue(boardDao.count()==0);

        BoardDto board = new BoardDto("title1", "content1", "writer1");
        assertTrue(boardDao.insert(board)==1);
        assertTrue(boardDao.count()==1);

        Integer bno = boardDao.selectAll().get(0).getBno();

        // board 삭제후 count
        assertTrue(boardDao.delete(bno, board.getWriter())==1);
        assertTrue(boardDao.count()==0);

        // board 추가
        assertTrue(boardDao.insert(board)==1);


        // 작성자를 writer+11 delete, count 할경우 존재하지 않는 writer이기때문에 count는 그대로
        assertTrue(boardDao.delete(bno, board.getWriter()+"123")==0);
        assertTrue(boardDao.count()==1);
    }

    @Test
    public void deleteAllTest() throws Exception {
        boardDao.deleteAll();
        assertTrue(boardDao.count()==0);

        BoardDto board = new BoardDto("title2", "content2", "writer2");
        assertTrue(boardDao.insert(board)==1);
        assertTrue(boardDao.count()==1);

        boardDao.deleteAll();
        assertTrue(boardDao.count()==0);

        // board 2개 추가후 count가 2인지
        assertTrue(boardDao.insert(board)==1);
        assertTrue(boardDao.insert(board)==1);
        assertTrue(boardDao.count()==2);
        
        // board 2개중에 1개삭제
        int bno = boardDao.selectAll().get(0).getBno();
        assertTrue(boardDao.delete(bno, board.getWriter())==1);
        assertTrue(boardDao.count()==1);
        assertTrue(boardDao.deleteAll()==1);
    }

    @Test
    public void insertTest() throws Exception{
        boardDao.deleteAll();
        assertTrue(boardDao.count()==0);

        // board 3개 추가후 count
        BoardDto board = new BoardDto("title3","content3","writer3");
        assertTrue(boardDao.insert(board)==1);
        assertTrue(boardDao.insert(board)==1);
        assertTrue(boardDao.insert(board)==1);
        assertTrue(boardDao.count()==3);

        // 0번째 bno 삭제후 count 2개확인
        int bno = boardDao.selectAll().get(0).getBno();
        assertTrue(boardDao.delete(bno,board.getWriter())==1);
        assertTrue(boardDao.count()==2);
        assertTrue(boardDao.deleteAll()==2);
    }

    @Test
    public void selectAllTest() throws Exception{
        boardDao.deleteAll();
        assertTrue(boardDao.count()==0);

        // board 2개 추가후 count
        BoardDto board = new BoardDto("title4", "content3", "Writer3");
        assertTrue(boardDao.insert(board)==1);
        assertTrue(boardDao.insert(board)==1);
        assertTrue(boardDao.count()==2);

        // 추가한 board 가 같은집 비교
        int bno = boardDao.selectAll().get(0).getBno();
        int bno2 = boardDao.selectAll().get(1).getBno();
        assertFalse(boardDao.select(bno).equals(boardDao.select(bno2).toString()));

        // 모두삭제후 count
        boardDao.deleteAll();
        assertTrue(boardDao.count()==0);
    }

    @Test
    public void selectTest() throws Exception {
        boardDao.deleteAll();
        assertTrue(boardDao.count()==0);

        BoardDto board = new BoardDto("title5", "content5", "writer5");
        assertTrue(boardDao.insert(board)==1);
        assertTrue(boardDao.selectAll().size()==1);
        assertTrue(boardDao.count()==1);

        assertTrue(boardDao.insert(board)==1);
        assertTrue(boardDao.count()==2);
    }

    @Test
    public void updateTest() throws Exception {
        boardDao.deleteAll();
        assertTrue(boardDao.count()==0);


        // board 생성
        BoardDto board = new BoardDto("title6", "content6", "writer6");
        assertTrue(boardDao.insert(board)==1);

        // 생성한 bno 번호를 가져온후 가져온 bno를 set해서 변경하고 Title 변경
        int bno = boardDao.selectAll().get(0).getBno();
        board.setBno(bno);
        board.setTitle("change");
        assertTrue(boardDao.update(board)==1);

        // 수정전 bno를 가져와서 board2에 저장후 같은객체인지 확인
        BoardDto board2 = boardDao.select(bno);
        assertTrue(board.equals(board2));
    }

    @Test
    public void selectPage() {
    }

    @Test
    public void updateCommentCnt() {
    }

    @Test
    public void increaseViewCnt() {
    }
}
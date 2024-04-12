package com.fastcampus.ch3.controller;

import com.fastcampus.ch3.dao.BoardDao;
import com.fastcampus.ch3.domain.BoardDto;
import com.fastcampus.ch3.domain.PageHandler;
import com.fastcampus.ch3.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardService boardService;
    @PostMapping("/modify")
    public String modify(BoardDto boardDto, Integer curPage, Integer pageSize,Model m, HttpSession session, RedirectAttributes rattr){
        String writer = (String) session.getAttribute("userId");
        boardDto.setWriter(writer);
        try {
            m.addAttribute("curPage", curPage);
            m.addAttribute("pageSize", pageSize);

            int rowCnt = boardService.update(boardDto);

            if(rowCnt!=1)
                throw new Exception("board modify err");
            rattr.addFlashAttribute("msg","MODIFY_OK");
            return "redirect:/board/list";
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("boardDto",boardDto);
            rattr.addFlashAttribute("msg","MODIFY_ERR");
            return "boardGetRead";
        }
    }

    @PostMapping("/write")
    public String write(BoardDto boardDto, Model m, HttpSession session, RedirectAttributes rattr){
        try {
            String writer = (String) session.getAttribute("userId");
            boardDto.setWriter(writer);
            int rowCnt = boardService.write(boardDto);
            if(rowCnt!=1)
                throw new Exception("board write err");
            rattr.addFlashAttribute("msg","WRT_OK");
            return "redirect:/board/list";
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("boardDto",boardDto);
            rattr.addFlashAttribute("msg","WRT_ERR");
            return "boardGetRead";
        }
    }
    @GetMapping("/write")
    public String write(Model m){
        // 쓰기일경우 쓰기폼만 보여주면된다
        // 모델에 mode를 new 로 담아서 view에 전송
        m.addAttribute("mode", "new");
        return "boardGetRead"; // 읽기와 쓰기 사용
    }
    @PostMapping("/remove")
    public String remove(Integer bno, Integer curPage, Integer pageSize, Model m, HttpSession session, RedirectAttributes rattr){
        // 선택한 bno와 일치하는 writer 작성자의 게시물을 삭제한다
        // 삭제될경우 redirect:/board/list?curPage=?&pageSize=? 로 리턴
        // 하여 view로 전송
        String writer = (String)session.getAttribute("userId");
        try {
            m.addAttribute("curPage", curPage);
            m.addAttribute("pageSize", pageSize);

            int rowCnt = boardService.remove(bno, writer);

            // 에러가 발생할경우 에러를던짐
            if(rowCnt!=1)
                throw new Exception("board remove err");
            // 삭제할경우 DEL_OK 메시지 리턴
            rattr.addFlashAttribute("msg", "DEL_OK");
        } catch (Exception e) {
            e.printStackTrace();
            // 에러가 발생할경우 DEL_ERR를 리턴
            rattr.addFlashAttribute("msg","DEL_ERR");
        }

        return "redirect:/board/list";
    }

    @GetMapping("/read")
    public String read(Integer bno, Integer curPage, Integer pageSize, Model m){
        // 선택한 bno의 게시물을 읽어온다 /board/list?bno=?
        // boardService의 read를 호출한다
        // 모델에 현재페이지번호와 한페이지당 게시물 출력개수인 pageSize를담아 뷰에 전송한다.
        // 왜냐? 목록버튼을 누를경우 리턴되는 페이지로 가야하기때문에 뷰로 전송
        try {
           BoardDto boardDto = boardService.read(bno);
           m.addAttribute(boardDto);
           m.addAttribute("curPage", curPage);
           m.addAttribute("pageSize", pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "boardGetRead";
    }

    @GetMapping("/list")
    public String list(Integer curPage, Integer pageSize, Model m, HttpServletRequest request){
        String toURL = request.getRequestURL().toString();
        if(!loginCheck(request)){
            return "redirect:/user/login?toURL="+toURL;
        }

        if(curPage==null) curPage=1;
        if(pageSize==null) pageSize=20;

        try {
            int totalCnt = boardService.getCount();
            PageHandler ph = new PageHandler(totalCnt, curPage, pageSize);

            Map map = new HashMap();
            map.put("offset", (curPage-1)*pageSize);
            map.put("pageSize", pageSize);
            System.out.println(map);
            List<BoardDto> list = boardService.getPage(map);
            m.addAttribute("list", list); // 게시물 가져오기
            m.addAttribute("ph", ph); // 페이징가져오기
            m.addAttribute("curPage", curPage);
            m.addAttribute("pageSize", pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "boardList";
    }

    private boolean loginCheck(HttpServletRequest request){
        HttpSession session = request.getSession();
        return session.getAttribute("userId")!=null;
    }
}

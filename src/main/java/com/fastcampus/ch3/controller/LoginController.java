package com.fastcampus.ch3.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class LoginController {
    @GetMapping("/login")
    public String loginForm(){
        System.out.println("loginForm 출력");
        return "loginForm";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        System.out.println("logout");
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(String userId, String pwd, String toURL, boolean remember, HttpServletRequest request, HttpServletResponse response) throws Exception{
        if(!loginCheck(userId,pwd)){
            System.out.println("로그인실패");
            return "redirect:/user/login";
        }

        HttpSession session = request.getSession();
        session.setAttribute("userId", userId);

        if(remember){
            Cookie cookie = new Cookie("userId", userId);
            response.addCookie(cookie);
        } else {
            Cookie cookie = new Cookie("userId", userId);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }

        toURL = toURL==null || toURL.equals("") ? "/" : toURL;
        System.out.println(toURL);
        return "redirect:"+toURL;
    }

    private boolean loginCheck(String userId, String pwd){
        return "qwer".equals(userId)&&"1234".equals(pwd);
    }

}
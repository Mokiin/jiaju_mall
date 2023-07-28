package com.kiin.furns.controller;

import com.kiin.furns.entity.Member;
import com.kiin.furns.service.MemberService;
import com.kiin.furns.service.impl.MemberServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/member"})
public class MemberController extends BasicController {

    private MemberService memberService = new MemberServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    private  void login(HttpServletRequest req, HttpServletResponse resp){

        String username = req.getParameter("username");
        String password = req.getParameter("userPassword");

        Member member = new Member();
        member.setUsername(username);
        member.setPassword(password);

        try {
            if (memberService.login(member)) {
                req.getRequestDispatcher("/views/member/login_ok.html").forward(req,resp);
            }else {
                req.setAttribute("msg","用户名或密码错误");
                req.setAttribute("username",username);
                req.getRequestDispatcher("/views/member/login.jsp").forward(req,resp);
            }
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void register(HttpServletRequest req, HttpServletResponse resp){
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        try {
            if (!memberService.isExistsUsername(username)){
                // 用户不存在才允许注册
                if (memberService.registerMember(new Member(null,username,password,email))){
                    // 注册成功
                    req.getRequestDispatcher("/register_ok.html").forward(req, resp);
                }else {
                    // 注册失败
                    req.getRequestDispatcher("/register_fail.html").forward(req,resp);
                }

            }else {
                // 用户存在，禁止重复注册
                req.getRequestDispatcher("/views/member/login.jsp").forward(req,resp);
            }
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

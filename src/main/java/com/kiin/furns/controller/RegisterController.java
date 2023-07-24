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

@WebServlet("/register")
public class RegisterController extends HttpServlet {

    private MemberService memberService = new MemberServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        if (!memberService.isExistsUsername(username)){
            // 用户不存在才允许注册
            if (memberService.registerMember(new Member(null,username,password,email))){
                // 注册成功
            }else {
                // 注册失败
            }

        }else {
            // 用户存在，禁止重复注册
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}

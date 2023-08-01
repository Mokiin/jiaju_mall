package com.kiin.furns.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.servlet.KaptchaServlet;
import com.kiin.furns.entity.Member;
import com.kiin.furns.service.MemberService;
import com.kiin.furns.service.impl.MemberServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

@WebServlet(urlPatterns = {"/member"})
public class MemberController extends BasicController {

    private MemberService memberService = new MemberServiceImpl();


    private  void login(HttpServletRequest req, HttpServletResponse resp){

        String username = req.getParameter("username");
        String password = req.getParameter("userPassword");

        Member member = new Member();
        member.setUsername(username);
        member.setPassword(password);

        try {
            if (memberService.login(member)) {
                HttpSession session = req.getSession();
                session.setAttribute("member",member);
                req.getRequestDispatcher("/views/member/login_ok.jsp").forward(req,resp);
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

    private void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        if (null != token && token.equalsIgnoreCase(code)){

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
        }else {
            req.setAttribute("msg","验证码错误");
            // 数据回显
            req.setAttribute("username",username);
            req.setAttribute("email",email);
            req.setAttribute("active","register");
            req.getRequestDispatcher("/views/member/login.jsp").forward(req,resp);
        }
    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath());
    }
}

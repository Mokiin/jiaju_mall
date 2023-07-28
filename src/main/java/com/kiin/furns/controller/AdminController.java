package com.kiin.furns.controller;

import com.kiin.furns.entity.Admin;
import com.kiin.furns.service.AdminService;
import com.kiin.furns.service.impl.AdminServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin")
public class AdminController extends BasicController {

    private AdminService adminService = new AdminServiceImpl();

    public void isAdmin(HttpServletRequest request, HttpServletResponse response) {
        String adminName = request.getParameter("adminName");
        String password = request.getParameter("AdminPassword");
        Admin admin = new Admin(null, adminName, password, null);
        try {
            if (adminService.queryAdmin(admin) != null) {
                request.getRequestDispatcher("/manager_menu.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/manager_login.jsp").forward(request, response);
            }
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

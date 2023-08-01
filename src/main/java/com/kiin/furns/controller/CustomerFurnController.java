package com.kiin.furns.controller;

import com.kiin.furns.entity.Furniture;
import com.kiin.furns.entity.Page;
import com.kiin.furns.service.FurnitureService;
import com.kiin.furns.service.impl.FurnitureServiceImpl;
import com.kiin.furns.utils.DataUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/customerFurnController")
public class CustomerFurnController extends BasicController{

    private FurnitureService furnitureService = new FurnitureServiceImpl();
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = DataUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = DataUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Furniture> page = furnitureService.page(pageNo, pageSize);
        req.setAttribute("page", page);
        req.getRequestDispatcher("/views/customer/index.jsp").forward(req, resp);
    }

    protected void pageByName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = DataUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = DataUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        String name = req.getParameter("name");
        if (null == name){
            name = "";
        }
        Page<Furniture> page = furnitureService.pageByName(pageNo, pageSize, name);
        StringBuilder url = new StringBuilder("customerFurnController?action=pageByName");
        if (!"".equals(name)){
            url.append("&name=").append(name);
        }
        page.setUrl(url.toString());
        req.setAttribute("page", page);
        req.getRequestDispatcher("/views/customer/index.jsp").forward(req, resp);
    }
}

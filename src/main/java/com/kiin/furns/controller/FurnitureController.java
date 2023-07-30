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
import java.nio.charset.Charset;
import java.util.List;

@WebServlet("/manage/furniture")
public class FurnitureController extends BasicController {

    private FurnitureService furnitureService = new FurnitureServiceImpl();

    public void queryFurniture(HttpServletRequest request, HttpServletResponse response) {

        List<Furniture> furnitures = furnitureService.queryFurniture();
        request.setCharacterEncoding(Charset.forName("UTF-8"));
        request.setAttribute("furniture", furnitures);
        try {
            request.getRequestDispatcher("/views/manager/furn_manager.jsp").forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void addFurniture(HttpServletRequest request, HttpServletResponse response) {
        Furniture furniture = null;
        try {
            furniture = DataUtils.copyParamToBean(request.getParameterMap(), new Furniture());
            furnitureService.addFurniture(furniture);
            response.sendRedirect("/manager/furniture?action=page&pageNo=" + request.getParameter("pageNo"));
        } catch (Exception e) {
            request.setAttribute("msg", "格式不正确");
        }

    }

    public void delFurnitureById(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        try {
            if (furnitureService.delFurnitureById(DataUtils.parseInt(id, 0))) {
                response.sendRedirect(request.getContextPath() + "/manager/furniture?action=page&pageNo=" + request.getParameter("pageNo"));
            } else {
                response.sendRedirect(request.getContextPath() + "/manage/furniture?action=queryFurniture");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void queryFurnitureById(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        Furniture furniture = furnitureService.queryFurnitureById(DataUtils.parseInt(id, 0));
        request.setAttribute("furn", furniture);
        // request.setAttribute("pageNo",request.getParameter("pageNo"));
        try {
            request.getRequestDispatcher("/views/manager/furn_update.jsp").forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void updateFurniture(HttpServletRequest request, HttpServletResponse response) {
        Furniture furniture = DataUtils.copyParamToBean(request.getParameterMap(), new Furniture());
        furnitureService.updateFurniture(furniture);
        String pageNo = request.getParameter("pageNo");
        try {
           // response.sendRedirect(request.getContextPath() + "/manage/furniture?action=queryFurniture&pageNo=" + pageNo);
            response.sendRedirect(request.getContextPath() + "/manage/furniture?action=page&pageNo=" + pageNo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = DataUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = DataUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Furniture> page = furnitureService.page(pageNo, pageSize);
        req.setAttribute("page", page);
        req.getRequestDispatcher("/views/manager/furn_manager.jsp").forward(req, resp);
    }
}

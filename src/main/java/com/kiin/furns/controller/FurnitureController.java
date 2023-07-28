package com.kiin.furns.controller;

import com.kiin.furns.entity.Furniture;
import com.kiin.furns.service.FurnitureService;
import com.kiin.furns.service.impl.FurnitureServiceImpl;
import com.kiin.furns.utils.DataUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.List;

@WebServlet("/manage/furniture")
public class FurnitureController extends BasicController{

    private FurnitureService service = new FurnitureServiceImpl();

    public void queryFurniture(HttpServletRequest request, HttpServletResponse response){

        List<Furniture> furnitures = service.queryFurniture();
        request.setCharacterEncoding(Charset.forName("UTF-8"));
        request.setAttribute("furniture",furnitures);
        try {
            request.getRequestDispatcher("/views/manager/furn_manager.jsp").forward(request,response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void addFurniture(HttpServletRequest request, HttpServletResponse response){
        Furniture furniture = null;
        try {
             furniture = DataUtils.copyParamToBean(request.getParameterMap(), new Furniture());
        } catch (Exception e) {
            request.setAttribute("msg","格式不正确");
            try {
                request.getRequestDispatcher("/views/manager/furn_add.jsp").forward(request,response);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            return;
        }

        service.addFurniture(furniture);

        try {
            response.sendRedirect(request.getContextPath() + "/manage/furniture?action=queryFurniture");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

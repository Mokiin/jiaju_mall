package com.kiin.furns.controller;

import com.kiin.furns.entity.Cart;
import com.kiin.furns.entity.CartItem;
import com.kiin.furns.entity.Furniture;
import com.kiin.furns.service.FurnitureService;
import com.kiin.furns.service.impl.FurnitureServiceImpl;
import com.kiin.furns.utils.DataUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;

@WebServlet("/cartController")
public class CartController extends BasicController {

    private FurnitureService furnitureService = new FurnitureServiceImpl();

    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取添加家具的 id
        int id = DataUtils.parseInt(req.getParameter("id"), 0);
        // 获取对应 id 的 furn
        Furniture furniture = furnitureService.queryFurnitureById(id);
        // 根据 furn 创建对应的 cart
        CartItem cartItem = new CartItem(furniture.getId(), furniture.getname(), 1, furniture.getPrice(), furniture.getPrice());
        // 从 session 中获取 cart
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        // 判断是否为 null
        if (null == cart) {
            // 为空，说明 session 中没有 cart 对象
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItems(cartItem);
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = DataUtils.parseInt(req.getParameter("id"), 0);
        int count = Integer.parseInt(req.getParameter("count"));
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (null != cart) {
            cart.updateCount(id, count);
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void delItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = DataUtils.parseInt(req.getParameter("id"), 0);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (null != cart) {
            cart.deleteItem(id);
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (null != cart) {
            cart.clear();
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }
}

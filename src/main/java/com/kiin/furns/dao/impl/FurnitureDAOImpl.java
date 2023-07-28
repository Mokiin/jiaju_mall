package com.kiin.furns.dao.impl;

import com.kiin.furns.dao.BasicDAO;
import com.kiin.furns.dao.FurnitureDAO;
import com.kiin.furns.entity.Furniture;

import java.util.List;

public class FurnitureDAOImpl extends BasicDAO<Furniture> implements FurnitureDAO {

    @Override
    public List<Furniture> queryFurniture() {
        String sql = "select 'id','name','maker','price','sales','stock' as imgPath from furniture";
        return queryMulti(sql, Furniture.class);
    }

    @Override
    public boolean addFurniture(Furniture furniture) {
        String sql = "insert into furniture (id,name, maker, price, sales, stock, img_path imgPath) values (null,?, ?,?,? ,?,?)";
        return update(sql, furniture.getname(), furniture.getMarker(), furniture.getPrice(), furniture.getSales(),furniture.getStock(),furniture.getImgPath()) != -1;
    }
}

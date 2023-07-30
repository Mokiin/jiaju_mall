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

    @Override
    public boolean delFurnitureById(int id) {
        String sql = "delete from furniture where id = ?";
        return update(sql, id) == 1;
    }

    @Override
    public Furniture queryFurnitureById(int id) {
        String sql = "select (id,name, maker, price, sales, stock, img_path imgPath) from furniture where id = ?";
        return querySingle(sql,Furniture.class,id);
    }

    @Override
    public boolean updateFurniture(Furniture furniture) {
        String sql = "update furniture set name = ? ,marker = ? , price = ? ,sales = ? ,stock = ? ,imgPath = ? where id = ?";
        return update(sql,furniture.getname(),furniture.getMarker(),furniture.getPrice(),furniture.getSales(),furniture.getStock(),furniture.getImgPath(),furniture.getId()) == 1;
    }

    @Override
    public int getTotalRow() {
        String sql = "select count(*) from furniture";
        return ((Number)queryScalar(sql)).intValue();
    }

    @Override
    public List<Furniture> getPageItems(int begin, int pageSize) {
        String sql = "select (id,name, maker, price, sales, stock, img_path imgPath) from furniture limit ? , ?";
        return queryMulti(sql,Furniture.class,begin,pageSize);
    }


}

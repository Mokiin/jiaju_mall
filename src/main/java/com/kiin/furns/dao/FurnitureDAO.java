package com.kiin.furns.dao;

import com.kiin.furns.entity.Furniture;

import java.util.List;

public interface FurnitureDAO {

    public List<Furniture> queryFurniture();

    public boolean addFurniture(Furniture furniture);

    public boolean delFurnitureById(int id);

    public Furniture queryFurnitureById(int id);

    public boolean updateFurniture(Furniture furniture);

    public int getTotalRow();

    public List<Furniture> getPageItems(int begin, int pageSize);

    public int getPageTotalRowByName(String name);

    public List<Furniture> getPageItemsByName(int begin, int pageSize, String name);
}

package com.kiin.furns.dao;

import com.kiin.furns.entity.Furniture;

import java.util.List;

public interface FurnitureDAO {

    public List<Furniture> queryFurniture();

    public boolean addFurniture(Furniture furniture);
}

package com.kiin.furns.service;

import com.kiin.furns.entity.Furniture;
import com.kiin.furns.entity.Page;

import java.util.List;

public interface FurnitureService {

    public List<Furniture> queryFurniture();

    public boolean addFurniture(Furniture furniture);

    public boolean delFurnitureById(Integer id);


    public Furniture queryFurnitureById(int id);

    public boolean updateFurniture(Furniture furniture);

    public Page<Furniture> page(int pageNo,int pageSize);

    public Page<Furniture> pageByName(int pageNo, int pageSize,String name);
}

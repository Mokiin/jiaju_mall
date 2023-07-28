package com.kiin.furns.service.impl;

import com.kiin.furns.dao.FurnitureDAO;
import com.kiin.furns.dao.impl.FurnitureDAOImpl;
import com.kiin.furns.entity.Furniture;
import com.kiin.furns.service.FurnitureService;

import java.util.List;

public class FurnitureServiceImpl implements FurnitureService {

    private FurnitureDAO furnitureDAO = new FurnitureDAOImpl();

    @Override
    public List<Furniture> queryFurniture(){
        return furnitureDAO.queryFurniture();
    }

    @Override
    public boolean addFurniture(Furniture furniture) {
        return furnitureDAO.addFurniture(furniture);
    }
}

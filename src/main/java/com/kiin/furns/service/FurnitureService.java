package com.kiin.furns.service;

import com.kiin.furns.entity.Furniture;

import java.util.List;

public interface FurnitureService {

    public List<Furniture> queryFurniture();

    public boolean addFurniture(Furniture furniture);
}

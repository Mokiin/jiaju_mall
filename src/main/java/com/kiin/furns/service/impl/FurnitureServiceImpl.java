package com.kiin.furns.service.impl;

import com.kiin.furns.dao.FurnitureDAO;
import com.kiin.furns.dao.impl.FurnitureDAOImpl;
import com.kiin.furns.entity.Furniture;
import com.kiin.furns.entity.Page;
import com.kiin.furns.service.FurnitureService;

import java.util.List;

public class FurnitureServiceImpl implements FurnitureService {

    private FurnitureDAO furnitureDAO = new FurnitureDAOImpl();

    @Override
    public List<Furniture> queryFurniture() {
        return furnitureDAO.queryFurniture();
    }

    @Override
    public boolean addFurniture(Furniture furniture) {
        return furnitureDAO.addFurniture(furniture);
    }

    @Override
    public boolean delFurnitureById(Integer id) {
        return furnitureDAO.delFurnitureById(id);
    }

    @Override
    public Furniture queryFurnitureById(int id) {
        return furnitureDAO.queryFurnitureById(id);
    }

    @Override
    public boolean updateFurniture(Furniture furniture) {
        return furnitureDAO.updateFurniture(furniture);
    }

    @Override
    public Page<Furniture> page(int pageNo, int pageSize) {
        Page<Furniture> page = new Page<Furniture>();

        page.setPageNo(pageNo);
        page.setPageSize(pageSize);

        int totalRow = furnitureDAO.getTotalRow();
        page.setTotalRow(totalRow);

        int totalCount = totalRow / pageSize;
        if (totalRow % pageSize > 0) {
            totalCount += 1;
        }
        page.setPageTotalCount(totalCount);

        int begin = (pageNo - 1) * pageSize;
        List<Furniture> items = furnitureDAO.getPageItems(begin, pageSize);
        page.setItems(items);
        return page;
    }

    /**
     *
     * @param pageNo 当前在第几页
     * @param pageSize 每页显示多少条数据
     * @param name
     * @return
     */
    @Override
    public Page<Furniture> pageByName(int pageNo, int pageSize, String name) {
        Page<Furniture> page = new Page<Furniture>();

        page.setPageNo(pageNo);
        page.setPageSize(pageSize);

        int totalRow = furnitureDAO.getPageTotalRowByName(name);
        page.setTotalRow(totalRow);

        int totalCount = totalRow / pageSize;
        if (totalRow % pageSize > 0) {
            totalCount += 1;
        }
        page.setPageTotalCount(totalCount);

        int begin = (pageNo - 1) * pageSize;
        List<Furniture> furnitures = furnitureDAO.getPageItemsByName(begin, pageSize, name);
        page.setItems(furnitures);
        return page;
    }
}

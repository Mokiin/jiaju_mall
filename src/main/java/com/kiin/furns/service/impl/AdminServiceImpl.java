package com.kiin.furns.service.impl;

import com.kiin.furns.dao.AdminDAO;
import com.kiin.furns.dao.impl.AdminDAOImpl;
import com.kiin.furns.entity.Admin;
import com.kiin.furns.service.AdminService;

public class AdminServiceImpl implements AdminService {

    private AdminDAO adminDAO = new AdminDAOImpl();
    @Override
    public Admin queryAdmin(Admin admin) {
        return adminDAO.queryAdmin(admin);
    }
}

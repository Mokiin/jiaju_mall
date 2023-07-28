package com.kiin.furns.dao.impl;

import com.kiin.furns.dao.BasicDAO;
import com.kiin.furns.dao.AdminDAO;
import com.kiin.furns.entity.Admin;

public class AdminDAOImpl extends BasicDAO<Admin> implements AdminDAO {
    @Override
    public Admin queryAdmin(Admin admin) {
        String sql = "select * from admin where name = ? and password = ?";
        return querySingle(sql, Admin.class,admin.getName(),admin.getPassword());
    }
}

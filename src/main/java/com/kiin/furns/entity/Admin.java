package com.kiin.furns.entity;

public class Admin {
    private Integer id;
    private String name;
    
    private String password;
    private Integer adminID;

    public Admin() {
    }

    public Admin(Integer id, String name, String password, Integer adminID) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.adminID = adminID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getadminID() {
        return adminID;
    }

    public void setadminID(Integer adminID) {
        this.adminID = adminID;
    }
}

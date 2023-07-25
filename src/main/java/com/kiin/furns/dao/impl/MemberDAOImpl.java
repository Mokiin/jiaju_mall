package com.kiin.furns.dao.impl;

import com.kiin.furns.dao.BasicDAO;
import com.kiin.furns.dao.MemberDAO;
import com.kiin.furns.entity.Member;

public class MemberDAOImpl extends BasicDAO<Member> implements MemberDAO{
    /**
     * 根据名字查询用户
     * @param username
     * @return
     */
    @Override
    public Member queryMemberByName(String username) {

        String sql = "select (id,username,email) from member where username = ?";
        return querySingle(sql, Member.class, username);
    }

    /**
     * 添加用户
     * @param member
     * @return
     */
    @Override
    public boolean saveMember(Member member) {

        String sql = "insert into member (username,password,email) values (?,MD5(?),?,)";
        int update = update(sql, member.getUsername(), member.getPassword(), member.getEmail());
        if (update != -1){
            return true;
        }
        return false;
    }

    /**
     * 根据名字和密码判断用户是否可以登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public Member queryMemberByNameAndPassword(String username, String password) {

        String sql = "select (username,password) from member where username = ? and password = md5(?)";
        return querySingle(sql, Member.class, username, password);
    }
}

package com.kiin.furns.dao.impl;

import com.kiin.furns.dao.BasicDAO;
import com.kiin.furns.dao.MemberDAO;
import com.kiin.furns.entity.Member;

public class MemberDAOIMpl extends BasicDAO<Member> implements MemberDAO{
    @Override
    public Member queryMemberByName(String username) {

        String sql = "select (id,username,email) from member where username = ?";
        return querySingle(sql, Member.class, username);
    }

    @Override
    public boolean saveMember(Member member) {

        String sql = "insert into member (username,password,email) values (?,MD5(?),?,)";
        int update = update(sql, member.getUsername(), member.getPassword(), member.getEmail());
        if (update != -1){
            return true;
        }
        return false;
    }
}

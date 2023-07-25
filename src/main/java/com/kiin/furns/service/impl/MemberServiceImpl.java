package com.kiin.furns.service.impl;

import com.kiin.furns.dao.MemberDAO;
import com.kiin.furns.dao.impl.MemberDAOImpl;
import com.kiin.furns.entity.Member;
import com.kiin.furns.service.MemberService;

public class MemberServiceImpl implements MemberService {

    private MemberDAO memberDAO = new MemberDAOImpl();
    @Override
    public boolean registerMember(Member member){
        // true 为更新成功
        return memberDAO.saveMember(member);
    }
    @Override
    public boolean isExistsUsername(String username) {
       return memberDAO.queryMemberByName(username) != null;

    }

    @Override
    public boolean login(Member member) {
        return memberDAO.queryMemberByNameAndPassword(member.getUsername(), member.getPassword()) != null;
    }
}

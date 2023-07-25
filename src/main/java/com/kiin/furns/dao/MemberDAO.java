package com.kiin.furns.dao;

import com.kiin.furns.entity.Member;

public interface MemberDAO {

    public Member queryMemberByName(String username);

    public boolean saveMember(Member member);

    public Member queryMemberByNameAndPassword(String username, String password);

}

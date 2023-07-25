package com.kiin.furns.service;

import com.kiin.furns.entity.Member;

public interface MemberService {

    public boolean registerMember(Member member);

    public boolean isExistsUsername(String username);

    public boolean login(Member member);
}

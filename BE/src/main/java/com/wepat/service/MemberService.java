package com.wepat.service;

import com.wepat.dto.MemberDto;
import com.wepat.entity.MemberEntity;

import java.util.concurrent.ExecutionException;

public interface MemberService {
    MemberEntity signUp(MemberDto member) throws ExecutionException, InterruptedException;
    MemberEntity signIn(String memberId, String pwd) throws ExecutionException, InterruptedException;
    MemberEntity findId(String email) throws ExecutionException, InterruptedException;
    MemberEntity modifyPwd(String memberId, String pwd) throws ExecutionException, InterruptedException;
    MemberEntity getMember(String memberId) throws ExecutionException, InterruptedException;
    MemberEntity modifyMember(MemberDto member) throws ExecutionException, InterruptedException;
    MemberEntity deleteMember(String memberId) throws ExecutionException, InterruptedException;
    MemberEntity logout(String memberId) throws ExecutionException, InterruptedException;

    // 관리자 기능
    MemberEntity warnMember(String memberId) throws ExecutionException, InterruptedException;
    MemberEntity blockMember(String memberId) throws ExecutionException, InterruptedException;
}

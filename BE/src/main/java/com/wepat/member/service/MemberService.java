package com.wepat.member.service;

import com.wepat.member.MemberDto;
import com.wepat.member.MemberEntity;

import javax.mail.MessagingException;
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

    void findPwd(String memberId, String email) throws ExecutionException, InterruptedException, MessagingException;

    MemberEntity addWarnMember(String memberId, String warnMemberId) throws ExecutionException, InterruptedException;

    MemberEntity addBlockMember(String memberId, String blockMemberId) throws ExecutionException, InterruptedException;

    public void saveRefreshToken(String memberId, String refreshToken) throws Exception;
    public String getRefreshToken(String memberId) throws Exception;
    public void deleteRefreshToken(String memberId) throws Exception;
}

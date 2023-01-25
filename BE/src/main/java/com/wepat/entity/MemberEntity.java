package com.wepat.entity;

import com.wepat.dto.MemberDto;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberEntity {
    public MemberEntity(MemberDto member) {
        this.pwd = member.getPwd();
        this.nickName = member.getNickName();
        this.email = member.getEmail();
        this.calendarId = member.getCalendarId();
        this.reportList = new ArrayList<>();
        this.block = new Boolean(false);
    }
    @ApiParam(value = "사용자 비밀번호", required = true)
    private String pwd;
    @ApiParam(value = "사용자 이름", required = true)
    private String nickName;
    @ApiParam(value = "사용자 E-mail", required = true)
    private String email;
    @ApiParam(value = "사용자의 달력 ID")
    private String calendarId;
    @ApiParam(value = "나를 신고한 회원 목록")
    private List<String> reportList;
    @ApiParam(value = "차단한 계정")
    private boolean block;
}

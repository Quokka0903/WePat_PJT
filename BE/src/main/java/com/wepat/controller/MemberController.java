package com.wepat.controller;

import com.wepat.dto.MemberDto;
import com.wepat.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.http.HttpResponse;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/member")
@CrossOrigin(origins = "*")
public class MemberController {
    private final Logger logger = LoggerFactory.getLogger(MemberController.class);
    private MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    @PostMapping("signup")
    @ApiOperation(value = "회원가입", notes = "정보를 받아 회원가입 시도한다.", response = MemberDto.class)
    public ResponseEntity<?> signUp(MemberDto member) {
        MemberDto memberResult = null;
        logger.info("signUp called!");
        try {
            memberResult = memberService.signUp(member);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<MemberDto>(memberResult, HttpStatus.OK);
    }
    @PostMapping("signin")
    @ApiOperation(value = "로그인 시도",  notes = "로그인 요청을 한다.",response = MemberDto.class)
    public ResponseEntity<?> signIn(String memberid, String pwd) {
        MemberDto memberResult = null;
        logger.info("signIn called!");
        try {
            memberResult = memberService.signIn(memberid, pwd);
            System.out.println(memberResult);
        } catch (ExecutionException e) {
            System.out.println("e1");
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            System.out.println("e2");
            throw new RuntimeException(e);
        }
        return new ResponseEntity<MemberDto>(memberResult, HttpStatus.OK);
    }
    @PostMapping("findid")
    @ApiOperation(value = "아이디 찾기", notes = "이메일을 확인하여 해당 아이디 제공", response = String.class)
    public ResponseEntity<?> findId(String name, String email) {
        MemberDto memberResult = null;
        logger.info("findId called!");
        try {
            memberResult = memberService.findId(email);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<MemberDto>(memberResult, HttpStatus.OK);
    }
    @PostMapping("findpwd")
    @ApiOperation(value = "비밀번호 찾기", notes = "아이디, 이메일 인증 성공 시," +
            "해당 이메일로 임시 비밀번호 제공 및 임시 비밀번호로 정보 변경", response = HttpResponse.class)
    public ResponseEntity<?> modifyPwd(String memberid, String pwd) {
        MemberDto memberResult = null;
        logger.info("findPwd called!");
        try {
            memberService.modifyPwd(memberid, pwd);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<MemberDto>(memberResult, HttpStatus.OK);
    }
    @GetMapping("/{memberid}")
    @ApiOperation(value = "마이페이지", notes = "현재 로그인되어있는 회원의 정보 조회", response = MemberDto.class)
    public ResponseEntity<?> getMember(@PathVariable String memberId) {
        MemberDto memberResult = null;
        logger.info("getMember called!");
        try {
            memberResult = memberService.getMember(memberId);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<MemberDto>(memberResult, HttpStatus.OK);
    }
    @PutMapping("/modify")
    @ApiOperation(value = "회원 정보 수정", notes = "현재 회원의 정보를 수정한다.", response = MemberDto.class)
    public ResponseEntity<?> modifyMember(MemberDto member) {
        MemberDto memberResult = null;
        logger.info("modifyMember called!");
        try {
            memberResult = memberService.modifyMember(member);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<MemberDto>(memberResult, HttpStatus.OK);
    }
    @DeleteMapping("/{memberid}")
    @ApiOperation(value = "사용자의 정보를 삭제한다.", response = HttpResponse.class)
    public ResponseEntity<?> deleteMember(@PathVariable String memberId) {
        MemberDto memberResult = null;
        logger.info("deleteMember called!");
        try {
            memberResult = memberService.deleteMember(memberId);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<MemberDto>(memberResult, HttpStatus.OK);
    }
    @GetMapping("/logout/{memberid}")
    @ApiOperation(value = "로그아웃", notes = "현재 로그인되어있는 사용자 로그아웃", response = HttpResponse.class)
    public ResponseEntity<?> logout(@PathVariable String memberId) {
        MemberDto memberResult = null;
        logger.info("logout called!");
        try {
            memberResult = memberService.logout(memberId);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<MemberDto>(memberResult, HttpStatus.OK);
    }
    @GetMapping("/warn/{memberid}")
    public ResponseEntity<?> warnMember(String memberId) {
        MemberDto memberResult = null;
        logger.info("warnMember called!");
        try {
            memberResult = memberService.warnMember(memberId);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<MemberDto>(memberResult, HttpStatus.OK);
    }
    @GetMapping("/block/{memberid}")
    public ResponseEntity<?> blockMember(String memberId) {
        MemberDto memberResult = null;
        logger.info("blockMember called!");
        try {
            memberResult = memberService.blockMember(memberId);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<MemberDto>(memberResult, HttpStatus.OK);
    }
}

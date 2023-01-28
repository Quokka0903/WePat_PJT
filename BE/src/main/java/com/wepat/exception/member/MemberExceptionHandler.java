package com.wepat.exception.member;

import com.wepat.controller.MemberController;
import com.wepat.exception.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(assignableTypes = {MemberController.class})
public class MemberExceptionHandler {

    @ExceptionHandler(IdWriteException.class)
    public ResponseEntity<?> IdWriteException(IdWriteException e) {
        ErrorDto errorDto = new ErrorDto("IdWriteException", "존재하지 않는 아이디입니다.");
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PwdWriteException.class)
    public ResponseEntity<?> PwdWriteException(PwdWriteException e) {
        ErrorDto errorDto = new ErrorDto("PwdWriteException", "비밀번호가 일치하지 않습니다.");
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExistEmailException.class)
    public ResponseEntity<?> ExistEmailException(ExistEmailException e) {
        ErrorDto errorDto = new ErrorDto("ExistEmailException", "이미 회원가입된 이메일입니다.");
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExistIdException.class)
    public ResponseEntity<?> ExistIdException(ExistIdException e) {
        ErrorDto errorDto = new ErrorDto("ExistIdException", "이미 존재하는 아이디입니다.");
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotExistEmailException.class)
    public ResponseEntity<?> NotExistEmailException(NotExistEmailException e) {
        ErrorDto errorDto = new ErrorDto("NotExistEmailException", "회원이 아닌 이메일입니다.");
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotExistMember.class)
    public ResponseEntity<?> NotExistMember(NotExistMember e) {
        ErrorDto errorDto = new ErrorDto("NotExistMember", "존재하지 않는 회원입니다");
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BlockMember.class)
    public ResponseEntity<?> BlockMember(BlockMember e) {
        ErrorDto errorDto = new ErrorDto("BlockMember", "차단된 계정입니다.");
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotExistCalendarException.class)
    public ResponseEntity<?> NotExistCalendarException(NotExistCalendarException e) {
        ErrorDto errorDto = new ErrorDto("NotExistCalendarException", "존재하지 않는 코드입니다.");
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorDto exHandler(Exception e) {
        return new ErrorDto("Exception", "서버에 오류가 발생했습니다.");
    }

}

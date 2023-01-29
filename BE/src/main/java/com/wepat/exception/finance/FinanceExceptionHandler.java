package com.wepat.exception.finance;

import com.wepat.exception.ErrorDto;
import com.wepat.finance.controller.FinanceController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(assignableTypes = {FinanceController.class})
public class FinanceExceptionHandler {

    @ExceptionHandler(OverMoney.class)
    public ResponseEntity<?> OverMoney(OverMoney e) {
        ErrorDto errorDto = new ErrorDto("OverMoney", e.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlreadyDeleteFinance.class)
    public ResponseEntity<?> AlreadyDeleteFinance(AlreadyDeleteFinance e) {
        ErrorDto errorDto = new ErrorDto("AlreadyDeleteFinance", "삭제된 가계부 내용입니다.");
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorDto exHandler(Exception e) {
        return new ErrorDto("Exception", "서버에 오류가 발생했습니다.");
    }
}

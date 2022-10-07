package com.lion.spring_paymodule.app.base.exceptionHandler;


import com.lion.spring_paymodule.app.base.dto.ResultData;
import com.lion.spring_paymodule.app.util.Util;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice // 모든 @RestController 에서 발생한 예외에 대한 제어권을 가로챈다.d
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultData> errorHandler(MethodArgumentNotValidException exception) {
        String msg = exception
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("/"));

        String data = exception
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getCode)
                .collect(Collectors.joining("/"));

        return Util.spring.responseEntityOf(ResultData.of("F-MethodArgumentNotValidException", msg, data));
    }
}

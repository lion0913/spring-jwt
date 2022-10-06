package com.lion.spring_paymodule.app.base.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResultData<T> {
    private String resultCode;
    private String msg;
    private T data;

    public static <T> ResultData<T> of(String resultCode, String msg, T data) {
        return new ResultData<>(resultCode, msg, data);
    }
    public static <T> ResultData<T> of(String resultCode, String msg) {
        return of(resultCode, msg, null);
    }


    public boolean isSuccess() {
        return resultCode.startsWith("S-");
    }

    public boolean isFail() {
        return isSuccess() == false;
    }
}

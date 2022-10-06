package com.lion.spring_paymodule.app.util;

import com.lion.spring_paymodule.app.base.dto.ResultData;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Util {
    public static class spring {
        public static <T> ResponseEntity<ResultData> responseEntityOf(ResultData<T> resultData) {
            return new ResponseEntity<>(resultData, null);
        }

        public static <T> ResponseEntity<ResultData> responseEntityOf(ResultData<T> resultData, HttpHeaders headers) {
            return new ResponseEntity<>(resultData, headers, resultData.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
        }
    }
}

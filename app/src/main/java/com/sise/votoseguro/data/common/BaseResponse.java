package com.sise.votoseguro.data.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseResponse<T> {
    private boolean success;
    private String message;
    private T data;

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<T>(true, "Ok", data);
    }

    public static <T> BaseResponse<T> error(String message) {
        return new BaseResponse<T>(true, message, null);
    }
}

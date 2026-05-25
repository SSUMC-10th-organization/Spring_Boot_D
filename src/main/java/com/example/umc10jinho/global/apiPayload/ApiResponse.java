package com.example.umc10jinho.global.apiPayload;

import com.example.umc10jinho.global.apiPayload.code.BaseErrorCode;
import com.example.umc10jinho.global.apiPayload.code.BaseSuccessCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private final boolean isSuccess;
    private final HttpStatus status;
    private final String code;
    private final String message;
    private final T result;

    public static <T> ApiResponse<T> onSuccess(BaseSuccessCode successCode, T result) {
        return ApiResponse.<T>builder()
                .isSuccess(true)
                .status(successCode.getStatus())
                .code(successCode.getCode())
                .message(successCode.getMessage())
                .result(result)
                .build();
    }

    public static <T> ApiResponse<T> onFailure(BaseErrorCode errorCode) {
        return ApiResponse.<T>builder()
                .isSuccess(false)
                .status(errorCode.getStatus())
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();
    }

    public static <T> ApiResponse<T> onFailure(BaseErrorCode errorCode, T data) {
        return ApiResponse.<T>builder()
                .isSuccess(false)
                .status(errorCode.getStatus())
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .result(data)
                .build();
    }
}

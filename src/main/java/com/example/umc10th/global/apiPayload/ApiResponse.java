package com.example.umc10th.global.apiPayload;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th.global.apiPayload.code.BaseSuccesscode;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class ApiResponse<T> {

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;

    @JsonProperty("code")
    private final String code;

    @JsonProperty("message")
    private final String message;

    @JsonProperty("result")
    private T result;

    // 성공한 경우 - 기본 성공 코드 사용
    public static <T> ApiResponse<T> onSuccess(T result) {
        return new ApiResponse<>(
                true,
                GeneralSuccessCode.OK.getCode(),
                GeneralSuccessCode.OK.getMessage(),
                result
        );
    }

    // 성공한 경우 - 성공 코드 직접 지정
    public static <T> ApiResponse<T> onSuccess(BaseSuccesscode code, T result) {
        return new ApiResponse<>(
                true,
                code.getCode(),
                code.getMessage(),
                result
        );
    }

    // 실패한 경우 - 에러 코드 직접 지정
    public static <T> ApiResponse<T> onFailure(BaseErrorCode code, T result) {
        return new ApiResponse<>(
                false,
                code.getCode(),
                code.getMessage(),
                result
        );
    }
}
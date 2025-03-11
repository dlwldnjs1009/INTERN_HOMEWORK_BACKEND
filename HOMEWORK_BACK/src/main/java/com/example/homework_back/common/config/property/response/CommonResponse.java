package com.example.homework_back.common.config.property.response;

import com.example.homework_back.common.config.property.status.BaseStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"httpStatus", "code", "message", "result"})
public class CommonResponse<T> {

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T result;

    public static <T> CommonResponse<T> of(BaseStatus status, T result) {
        return new CommonResponse<>(status.getReason().getStatus(),
                status.getReason().getCode(),
                status.getReason().getMessage(),
                result);
    }

    public static <T> CommonResponse<T> ok(BaseStatus status) {
        return new CommonResponse<T>(status.getReason().getStatus(),
                status.getReason().getCode(),
                status.getReason().getMessage(),
                null);
    }
}

package com.example.homework_back.common.config.property.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ReasonDto {
    private HttpStatus status;
    private String code;
    private String message;
}

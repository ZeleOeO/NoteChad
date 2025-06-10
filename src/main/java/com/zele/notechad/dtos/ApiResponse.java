package com.zele.notechad.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class ApiResponse<T> {
    private int status;
    private String message;
    private T data;
}

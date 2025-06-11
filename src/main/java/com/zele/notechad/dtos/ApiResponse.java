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

    @Override
    public String toString() {
        return String.format(
                """
                       "status": %s,
                       "message": %s,
                       "data": %s
                       """,
                status, message, data.toString()
        );
    }
}

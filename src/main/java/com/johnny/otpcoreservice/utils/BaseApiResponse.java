package com.johnny.otpcoreservice.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data

public class BaseApiResponse<T> {
    private String Status;
    private String message;
    private   T data;

    public BaseApiResponse(String status, String message, T data) {
        Status = status;
        this.message = message;
        this.data = data;
    }
}

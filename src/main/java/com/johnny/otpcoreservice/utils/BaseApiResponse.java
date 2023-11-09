package com.johnny.otpcoreservice.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ApiResponse<T> {
    private String Status;

    private String message;

    private   <T> data;
}

package com.johnny.otpcoreservice.Dto.response;

import com.johnny.otpcoreservice.domain.OtpRequest;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OtpResponseDto implements Serializable{


    private String otp;

    private String userId;

    private int ttl;
}

package com.johnny.otpcoreservice.services.criteria;

import com.johnny.otpcoreservice.domain.enumeration.OtpType;
import lombok.*;

import java.time.Instant;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OtpCriteria {

    private String otp;
    private String otpType;
    private String userId;
    private Integer ttl;
    private Integer otpLength;
    private Instant dateCreated;
    private Instant dateValidated;
    private OtpType status;
}

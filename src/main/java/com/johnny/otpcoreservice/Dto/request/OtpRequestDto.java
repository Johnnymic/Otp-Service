package com.johnny.otpcoreservice.Dto.request;

import com.johnny.otpcoreservice.domain.enumeration.OtpStatus;
import com.johnny.otpcoreservice.domain.enumeration.OtpType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.criteria.Path;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.apache.commons.lang3.builder.HashCodeExclude;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OtpRequestDto  implements Serializable {

    private String otpType;


    private String userId;

    private String otp;

    private Integer ttl;

    private Integer otpLength;

    private Instant dateCreated;

    private Instant dateValidated;

    private String status;



}

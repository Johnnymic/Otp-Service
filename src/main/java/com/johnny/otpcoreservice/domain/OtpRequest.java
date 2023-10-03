package com.johnny.otpcoreservice.domain;

import com.johnny.otpcoreservice.domain.enumeration.OtpStatus;
import com.johnny.otpcoreservice.domain.enumeration.OtpType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name= "otp_request")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class OtpRequest implements Serializable {
    private static  final long serialVersionUSID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "otp", nullable = false)
    private String otp;
    @Enumerated(EnumType.STRING)
    @Column(name = "otp_type", nullable = false)
    private OtpType otpType;
    @NotNull
    @Column(name = "user_id", nullable = false)
    private String userId;
    @NotNull
    @Column(name = "ttl", nullable = false)
    private Integer ttl;
    @NotNull
    @Column(name = "otp_length", nullable = false)
    private int otpLength;
    @Column(name = "otp_status",nullable = false)
    @Enumerated(EnumType.STRING)
    private OtpStatus status;


    private Instant dateCreated;

    private Instant dateValidated;



}

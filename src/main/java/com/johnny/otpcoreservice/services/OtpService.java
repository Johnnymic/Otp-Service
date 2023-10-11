package com.johnny.otpcoreservice.services;

import com.johnny.otpcoreservice.Dto.request.OtpRequestDto;
import com.johnny.otpcoreservice.Dto.response.OtpResponseDto;
import com.johnny.otpcoreservice.services.criteria.OtpCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface OtpService {

     OtpResponseDto saveOtp(OtpRequestDto otpRequestDto);

    Page<OtpResponseDto> findAll(Pageable pageable);

    Optional<OtpResponseDto> findOne(Long id);

    List<OtpRequestDto> findByCriteria(OtpCriteria criteria);

    Page<OtpRequestDto> findPageByCriteria(OtpCriteria criteria,Pageable pageable);

    void deleteOtp(Long id);
}

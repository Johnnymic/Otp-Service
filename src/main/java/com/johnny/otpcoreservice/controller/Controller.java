package com.johnny.otpcoreservice.controller;

import com.johnny.otpcoreservice.Dto.request.OtpRequestDto;
import com.johnny.otpcoreservice.Dto.response.OtpResponseDto;


import com.johnny.otpcoreservice.services.impl.ExtendedOtpService;
import com.johnny.otpcoreservice.utils.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/otp")
@RequiredArgsConstructor
@Slf4j
public class Controller {

   private final ExtendedOtpService extendedOtpService;
    @PostMapping()
    public ResponseEntity<ApiResponse> getOtp(@Valid @RequestBody OtpRequestDto otpRequest){
        ApiResponse apiResponse = extendedOtpService.getOtp(otpRequest);
        return  ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> isOtpValid(@Valid @RequestBody OtpResponseDto otpResponseDto){
        log.info("otp {}", otpResponseDto);
        ApiResponse apiResponse = extendedOtpService.isOtpValid(otpResponseDto);
        return  ResponseEntity.ok(apiResponse);
    }

}

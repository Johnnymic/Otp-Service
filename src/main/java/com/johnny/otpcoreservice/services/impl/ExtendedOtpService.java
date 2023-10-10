package com.johnny.otpcoreservice.services.impl;

import com.johnny.otpcoreservice.Dto.request.OtpRequestDto;
import com.johnny.otpcoreservice.Dto.response.OtpResponseDto;
import com.johnny.otpcoreservice.domain.OtpRequest;
import com.johnny.otpcoreservice.domain.enumeration.OtpStatus;
import com.johnny.otpcoreservice.domain.enumeration.OtpType;
import com.johnny.otpcoreservice.exceptions.ResourceNotFoundException;
import com.johnny.otpcoreservice.repository.OtpRepository;
import com.johnny.otpcoreservice.services.OtpService;
import com.johnny.otpcoreservice.services.criteria.OtpCriteria;
import com.johnny.otpcoreservice.services.criteria.OtpQueryService;
import com.johnny.otpcoreservice.utils.ApiResponse;
import com.johnny.otpcoreservice.utils.DefaultResponse;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.PropertyResourceBundle;

@Service
@RequiredArgsConstructor
public class ExtendedOtpService {

    private final org.slf4j.Logger log =LoggerFactory.getLogger(OtpServiceImpl.class);
    @Autowired
    private   OtpService otpService;

    private  final OtpRepository otpRepository;
    public ApiResponse getOtp(OtpRequestDto otpRequest) {
        log.info("payload {}", otpRequest);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(DefaultResponse.ResponseCode.FAILED.getCode());
        apiResponse.setMessage(DefaultResponse.ResponseCode.FAILED.getDescription());
   try {
       otpRequest.setStatus(String.valueOf(OtpStatus.SENT));
       otpRequest.setDateCreated(Instant.now());
       otpRequest.setDateValidated(Instant.now());
       String randomOtp = generateRandomOtp(otpRequest.getOtpLength());
       otpRequest.setOtp(randomOtp);
       if (StringUtils.isBlank(otpRequest.getUserId())){
           otpRequest.setUserId(generateRequestId());
       }
       otpService.saveOtp(otpRequest);
   }catch(Exception e){
        apiResponse.setStatus(DefaultResponse.ResponseStatus.API_ERROR_STATUS.getCode());
        apiResponse.setMessage(DefaultResponse.ResponseStatus.API_ERROR_STATUS.getDescription());
   }
        OtpResponseDto otpResponseDto = new OtpResponseDto();
        otpResponseDto.setUserId(otpRequest.getUserId());
        otpResponseDto.setTtl(otpRequest.getTtl());
        otpResponseDto.setOtp(otpRequest.getOtp());

        apiResponse.setData(otpResponseDto);
        apiResponse.setMessage(DefaultResponse.ResponseCode.SUCCESS.getDescription());
        apiResponse.setStatus(DefaultResponse.ResponseCode.SUCCESS.getCode());
        return apiResponse;

    }

    private String generateRequestId() {
        return String.valueOf(System.currentTimeMillis());
    }


    private static String generateRandomOtp(int otpLength) {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder randomNumber = new StringBuilder();

        for(int i=0; i<otpLength; i++){
            int digit = secureRandom.nextInt(10); // Generate a random digit between 0 and 9
            randomNumber.append(digit);
        }
       return randomNumber.toString();

    }


    public ApiResponse isOtpValid(OtpResponseDto otpResponseDto) {
        if(otpResponseDto.getOtp() !=null && otpResponseDto.getUserId() !=null){
          OtpCriteria otpCriteria = new OtpCriteria();
          otpCriteria.setOtp(otpResponseDto.getOtp());
          otpCriteria.setUserId(otpResponseDto.getUserId());

          List<OtpRequestDto> otpRequestDtos =otpService.findByCriteria(otpCriteria);
           if(StringUtils.isNotEmpty(otpRequestDtos.toString())){
               log.info("otpRequestDto {}", otpRequestDtos);
               return checkOtpExpiration(otpRequestDtos.get(0));
           }
           throw  new ResourceNotFoundException("Not found ");


        }
        return null;
    }

    private ApiResponse checkOtpExpiration(OtpRequestDto request) {
        ApiResponse response = new ApiResponse();
        Instant currentTime = Instant.now();
        Instant otpCreated = request.getDateCreated();
        Instant expiredOtpDate = otpCreated.plus(Duration.ofSeconds(request.getTtl()));
        if(expiredOtpDate.isBefore(currentTime)){
            request.setStatus(OtpStatus.EXPIRED.name());
            otpService.saveOtp(request);
           response.setMessage("Otp has expired");
           response.setStatus((DefaultResponse.ResponseCode.SUCCESS.getCode()));
           return response;
          }else{
            request.setDateValidated(Instant.now());
            request.setStatus(OtpStatus.VERIFIED.name());
            otpService.saveOtp(request);
            response.setStatus(DefaultResponse.ResponseCode.SUCCESS.getCode());
            response.setMessage("OTP is verified");
            return response;
        }
    }
}

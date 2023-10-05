package com.johnny.otpcoreservice.services.mapper;

import com.johnny.otpcoreservice.Dto.request.OtpRequestDto;
import com.johnny.otpcoreservice.Dto.response.OtpResponseDto;
import com.johnny.otpcoreservice.domain.OtpRequest;
import com.johnny.otpcoreservice.domain.enumeration.OtpStatus;
import com.johnny.otpcoreservice.domain.enumeration.OtpType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OtpMapper {
    public static OtpRequest mapToEntity(OtpRequestDto otpRequestDto){
        OtpRequest request = new OtpRequest();
        request.setOtp(otpRequestDto.getOtp());
        request.setTtl(otpRequestDto.getTtl());
        request.setOtp(otpRequestDto.getOtp());
        request.setUserId(otpRequestDto.getUserId());
        request.setOtpLength(otpRequestDto.getOtpLength());
        request.setStatus(OtpStatus.valueOf(otpRequestDto.getStatus()));
        request.setDateCreated(otpRequestDto.getDateCreated());
        request.setDateValidated(otpRequestDto.getDateCreated());
        request.setOtpType(OtpType.valueOf(otpRequestDto.getOtpType()));
        return request;
    }

    public static OtpResponseDto mapToDto(OtpRequest otpRequest){
        OtpResponseDto response = new OtpResponseDto();
        response.setOtp(otpRequest.getUserId());
        response.setUserId(otpRequest.getUserId());
        return response;
    }
    public static OtpRequestDto mapToOtpRequest(OtpRequest otpRequest){
        OtpRequestDto request = new OtpRequestDto();
        BeanUtils.copyProperties(otpRequest,request);
        return request;
    }

    public static List<OtpRequestDto> mapToDtos(List<OtpRequest> otpRequests){
        List<OtpRequestDto> requestList = new ArrayList<>();
        for (OtpRequest otpRequest : otpRequests) {
            OtpRequestDto request = new OtpRequestDto();
            request.setOtp(otpRequest.getOtp());
            request.setTtl(otpRequest.getTtl());
            request.setUserId(otpRequest.getUserId());
            request.setOtpLength(otpRequest.getOtpLength());
            request.setStatus(String.valueOf(otpRequest.getStatus()));
            request.setDateCreated(otpRequest.getDateCreated());
            request.setDateValidated(otpRequest.getDateValidated());
            request.setOtpType(String.valueOf(otpRequest.getOtpType()));
            requestList.add(request);
        }
       return requestList;
    }



}

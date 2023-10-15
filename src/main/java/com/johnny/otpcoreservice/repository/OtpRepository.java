package com.johnny.otpcoreservice.repository;

import com.johnny.otpcoreservice.Dto.request.OtpRequestDto;
import com.johnny.otpcoreservice.Dto.response.OtpResponseDto;
import com.johnny.otpcoreservice.domain.OtpRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface OtpRepository extends JpaRepository<OtpRequest, Long> , JpaSpecificationExecutor<OtpRequest> {


}

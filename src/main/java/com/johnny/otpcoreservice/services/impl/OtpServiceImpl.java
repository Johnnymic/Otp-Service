package com.johnny.otpcoreservice.services.impl;

import com.johnny.otpcoreservice.Dto.request.OtpRequestDto;
import com.johnny.otpcoreservice.Dto.response.OtpResponseDto;
import com.johnny.otpcoreservice.domain.OtpRequest;
import com.johnny.otpcoreservice.repository.OtpRepository;
import com.johnny.otpcoreservice.services.OtpService;
import com.johnny.otpcoreservice.services.criteria.OtpCriteria;
import com.johnny.otpcoreservice.services.mapper.OtpMapper;
import com.johnny.otpcoreservice.utils.OtpSpecifications;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {
    private final Logger log =LoggerFactory.getLogger(OtpServiceImpl.class);
    private final OtpRepository otpRepository;


    @Override
    public OtpResponseDto saveOtp(OtpRequestDto otpRequestDto) {
       log.info("save otpRequestDto {}",otpRequestDto);
        OtpRequest otpRequest = OtpMapper.mapToEntity(otpRequestDto);
        log.info("save otpRequest {}",otpRequest);
        var saveOtp = otpRepository.save(otpRequest);
        return  OtpMapper.mapToDto(saveOtp);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OtpResponseDto> findAll(Pageable pageable) {
        return otpRepository.findAll(pageable).map(OtpMapper::mapToDto);
    }

    public List<OtpResponseDto> findAll(String userId, String otp){
        return otpRepository.findAll().stream().map(OtpMapper::mapToDto).collect(Collectors.toList());
    }
    @Override
    @Transactional(readOnly = true)
    public Optional<OtpResponseDto> findOne(Long id) {
        return otpRepository.findById(id).map(OtpMapper::mapToDto);
    }

    @Override
    public void  deleteOtp(Long id) {
        otpRepository.deleteById(id);
    }
    @Override
    @Transactional(readOnly = true)
    public List<OtpRequestDto> findByCriteria(OtpCriteria criteria) {
        Specification<OtpRequest> specification = OtpSpecifications.byCriteria(criteria);
        List<OtpRequest> otpRequests = otpRepository.findAll(specification);
        return OtpMapper.mapToDtos(otpRequests);
    }
    @Override
    @Transactional(readOnly = true)
    public Page<OtpRequestDto> findPageByCriteria(OtpCriteria criteria,Pageable pageable){
        Specification<OtpRequest> specification = OtpSpecifications.byCriteria(criteria);
        return  otpRepository.findAll(specification,pageable).map(OtpMapper::mapToOtpRequest);
    }


}

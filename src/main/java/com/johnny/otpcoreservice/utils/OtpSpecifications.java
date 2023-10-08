package com.johnny.otpcoreservice.utils;

import com.johnny.otpcoreservice.Dto.request.OtpRequestDto;
import com.johnny.otpcoreservice.domain.OtpRequest;
import com.johnny.otpcoreservice.services.criteria.OtpCriteria;
import com.johnny.otpcoreservice.services.mapper.OtpMapper;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public class OtpSpecifications {
    public static Specification<OtpRequest> byCriteria(OtpCriteria criteria) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (criteria != null) {
                if (criteria.getOtp() != null) {
                    predicates.add(criteriaBuilder.like(root.get("otp"), "%" + criteria.getOtp() + "%"));
                }
                if (criteria.getOtpType() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("otpType"), criteria.getOtpType()));
                }
                if (criteria.getUserId() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("userId"), criteria.getUserId()));
                }
                if (criteria.getTtl() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("ttl"), criteria.getTtl()));
                }
                if (criteria.getOtpLength() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("otpLength"), criteria.getOtpLength()));
                }
                if (criteria.getDateCreated() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("dateCreated"), criteria.getDateCreated()));
                }
                if (criteria.getDateValidated() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("dateValidated"), criteria.getDateValidated()));
                }
                if (criteria.getStatus() != null) {
                    predicates.add(criteriaBuilder.equal(root.get("status"), criteria.getStatus()));
                }
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}

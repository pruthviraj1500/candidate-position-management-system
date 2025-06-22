package com.programming.candidate_position_management_system.service;

import com.programming.candidate_position_management_system.request.CandidateRequest;
import com.programming.candidate_position_management_system.response.CandidateResponse;

import java.util.List;

public interface CandidateService {
    CandidateResponse createCandidate(CandidateRequest request);
    CandidateResponse updateCandidate(Long id, CandidateRequest request);
    CandidateResponse getCandidateById(Long id);
    List<CandidateResponse> getAllCandidates();
}

package com.programming.candidate_position_management_system.service;

import com.programming.candidate_position_management_system.request.PositionRequest;
import com.programming.candidate_position_management_system.response.PositionResponse;

import java.util.List;

public interface PositionService {
    PositionResponse createPosition(PositionRequest request);
    List<PositionResponse> getAllPositions();
    PositionResponse getPositionById(Long id);
}

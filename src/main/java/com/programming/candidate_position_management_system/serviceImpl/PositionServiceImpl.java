package com.programming.candidate_position_management_system.serviceImpl;

import com.programming.candidate_position_management_system.custom_exception.ResourceNotFoundException;
import com.programming.candidate_position_management_system.model.Position;
import com.programming.candidate_position_management_system.repository.PositionRepository;
import com.programming.candidate_position_management_system.request.PositionRequest;
import com.programming.candidate_position_management_system.response.PositionResponse;
import com.programming.candidate_position_management_system.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;
    private final ModelMapper mapper;

    @Override
    public PositionResponse createPosition(PositionRequest positionRequest) {
        if (positionRepository.existsByPositionName(positionRequest.getPositionName())) {
            throw new IllegalArgumentException("Position already exists");
        }
        Position position = mapper.map(positionRequest, Position.class);
        return mapper.map(positionRepository.save(position), PositionResponse.class);
    }

    @Override
    public List<PositionResponse> getAllPositions() {
        return positionRepository.findAll()
                .stream()
                .map(position -> mapper.map(position, PositionResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public PositionResponse getPositionById(Long id) {
        Position position = positionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Position not found for id : "+id));
        return mapper.map(position, PositionResponse.class);
    }

}

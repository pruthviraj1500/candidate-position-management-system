package com.programming.candidate_position_management_system.serviceImpl;

import com.programming.candidate_position_management_system.custom_exception.ResourceNotFoundException;
import com.programming.candidate_position_management_system.model.Candidate;
import com.programming.candidate_position_management_system.model.Position;
import com.programming.candidate_position_management_system.repository.CandidateRepository;
import com.programming.candidate_position_management_system.repository.PositionRepository;
import com.programming.candidate_position_management_system.request.CandidateRequest;
import com.programming.candidate_position_management_system.response.CandidateResponse;
import com.programming.candidate_position_management_system.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;
    private final PositionRepository positionRepository;
    private final ModelMapper mapper;

    @Override
    public CandidateResponse createCandidate(CandidateRequest request) {
        validateCandidateRequest(request);

        Candidate candidate = mapper.map(request, Candidate.class);
        List<Position> positions = positionRepository.findAllById(request.getPositionIds());
        if (positions.size() != request.getPositionIds().size()) {
            throw new IllegalArgumentException("Some position IDs are invalid.");
        }
        candidate.setPositions(positions);

        return mapper.map(candidateRepository.save(candidate), CandidateResponse.class);
    }

    @Override
    public CandidateResponse updateCandidate(Long id, CandidateRequest request) {
        Candidate detachedCandidate = candidateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found for id : "+id));

        if (request.getName() != null) detachedCandidate.setName(request.getName());
        if (request.getEmail() != null) detachedCandidate.setEmail(request.getEmail());
        if (request.getDob() != null) detachedCandidate.setDob(request.getDob());
        if (request.getPositionIds() != null) {
            List<Position> positions = positionRepository.findAllById(request.getPositionIds());
            detachedCandidate.setPositions(positions);
        }

        return mapper.map(candidateRepository.save(detachedCandidate), CandidateResponse.class);
    }

    @Override
    public CandidateResponse getCandidateById(Long id) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found for id : "+id));
        return mapper.map(candidate, CandidateResponse.class);
    }

    @Override
    public List<CandidateResponse> getAllCandidates() {
        return candidateRepository.findAll()
                .stream()
                .map(candidate -> mapper.map(candidate, CandidateResponse.class))
                .collect(Collectors.toList());
    }

    private void validateCandidateRequest(CandidateRequest request) {
        if (candidateRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        if (Period.between(request.getDob(), LocalDate.now()).getYears() < 18) {
            throw new IllegalArgumentException("Candidate must be at least 18 years old");
        }
    }

}

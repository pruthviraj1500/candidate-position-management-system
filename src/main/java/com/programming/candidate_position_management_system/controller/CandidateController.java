package com.programming.candidate_position_management_system.controller;


import com.programming.candidate_position_management_system.request.CandidateRequest;
import com.programming.candidate_position_management_system.response.CandidateResponse;
import com.programming.candidate_position_management_system.service.CandidateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;

    @PostMapping
    public ResponseEntity<CandidateResponse> createCandidate(@RequestBody @Valid CandidateRequest candidateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(candidateService.createCandidate(candidateRequest));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CandidateResponse> updateCandidate(
            @PathVariable Long id,
            @RequestBody CandidateRequest candidateRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(candidateService.updateCandidate(id, candidateRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateResponse> getCandidateById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(candidateService.getCandidateById(id));
    }

    @GetMapping
    public ResponseEntity<List<CandidateResponse>> getAllCandidates() {
        return ResponseEntity.status(HttpStatus.OK).body(candidateService.getAllCandidates());
    }

}

package com.programming.candidate_position_management_system.controller;


import com.programming.candidate_position_management_system.request.PositionRequest;
import com.programming.candidate_position_management_system.response.PositionResponse;
import com.programming.candidate_position_management_system.service.PositionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/positions")
@RequiredArgsConstructor
public class PositionController {

    private final PositionService positionService;

    @PostMapping
    public ResponseEntity<PositionResponse> createPosition(@RequestBody @Valid PositionRequest positionRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(positionService.createPosition(positionRequest));
    }

    @GetMapping
    public ResponseEntity<List<PositionResponse>> getAllPositions() {
        return ResponseEntity.status(HttpStatus.OK).body(positionService.getAllPositions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PositionResponse> getPositionById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(positionService.getPositionById(id));
    }

}

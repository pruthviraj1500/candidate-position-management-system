package com.programming.candidate_position_management_system.response;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CandidateResponse {
    private Long id;
    private String name;
    private String email;
    private LocalDate dob;
    private List<PositionResponse> positions;
}

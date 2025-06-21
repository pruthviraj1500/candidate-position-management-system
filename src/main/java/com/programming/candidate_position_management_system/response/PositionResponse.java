package com.programming.candidate_position_management_system.response;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PositionResponse {
    private Long id;
    private String positionName;
}

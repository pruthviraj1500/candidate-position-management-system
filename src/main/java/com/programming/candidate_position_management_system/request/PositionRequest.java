package com.programming.candidate_position_management_system.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PositionRequest {

    @NotBlank(message = "Position name is required")
    @Size(max = 50, message = "Position name must not exceed 50 characters")
    private String positionName;

}

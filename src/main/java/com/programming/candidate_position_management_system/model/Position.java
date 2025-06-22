package com.programming.candidate_position_management_system.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "positions")
public class Position extends BaseEntity{

    @Column(length = 50, unique = true, nullable = false)
    private String positionName;

    @ManyToMany(mappedBy = "positions", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Candidate> candidates;

}

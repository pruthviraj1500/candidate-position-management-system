package com.programming.candidate_position_management_system.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "positions")
public class Position extends BaseEntity{

    @Column(length = 50, unique = true, nullable = false)
    private String positionName;

    @ManyToMany(mappedBy = "positions")
    private List<Candidate> candidates;

}

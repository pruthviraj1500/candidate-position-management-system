package com.programming.candidate_position_management_system.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "candidates")
public class Candidate extends BaseEntity{

    @Column(length = 50, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private LocalDate dob;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "candidate_position",
            joinColumns = @JoinColumn(name = "candidate_id"),
            inverseJoinColumns = @JoinColumn(name = "position_id")
    )
    @JsonManagedReference
    private List<Position> positions;

}

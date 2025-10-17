package com.pathshala.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "INSURANCE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String insuranceProvider;

    private LocalDate validUntil;

    private String insuranceNumber;

    @OneToOne(mappedBy = "insurance")
    private Patient patient;
}

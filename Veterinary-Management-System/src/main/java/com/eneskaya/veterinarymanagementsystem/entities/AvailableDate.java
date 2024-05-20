package com.eneskaya.veterinarymanagementsystem.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "AvailableDate")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "available_date_id")
    private Long id;

    @Column(name = "available_date", nullable = false)
    @FutureOrPresent
    private LocalDate availableDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "available_date_doctor_id", referencedColumnName = "doctor_id")
    private Doctor doctor;
}

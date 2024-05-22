package com.eneskaya.veterinarymanagementsystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "REPORT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long id;
//
//    @Column(name = "TITLE", length = 100, nullable = false)
//    private String title;
//
//    @Column(name = "DIAGNOSIS", length = 100, nullable = false)
//    private String diagnosis;
//
//    @Column(name = "PRICE", nullable = false)
//    private double price;
//
//    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
//    @JoinColumn(name = "APPOINTMENT_ID")
//    private Appointment appointment;
//
//    @OneToMany(mappedBy = "report", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
//    @JsonIgnore
//    private List<Vaccine> vaccineList;
}

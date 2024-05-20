package com.eneskaya.veterinarymanagementsystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Doctor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private Long id;

    @Column(name = "doctor_name", nullable = false)
    private String name;

    @Column(name = "doctor_phone", nullable = false, columnDefinition = "VARCHAR(11)")
    private String phone;

    @Column(name = "doctor_mail", nullable = false)
    @Email
    private String mail;

    @Column(name = "doctor_address")
    private String address;

    @Column(name = "doctor_city", nullable = false)
    private String city;

    @JsonIgnore
    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Appointment> appointmentList;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<AvailableDate> availableDateList;
}

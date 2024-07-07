package com.eneskaya.veterinarymanagementsystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Animal")
@Data
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id")
    private Long id;

    @Column(name = "animal_name", nullable = false)
    private String name;

    @Column(name = "animal_species", nullable = false)
    private String species;

    @Column(name = "animal_breed", nullable = false)
    private String breed;

    @Column(name = "animal_gender", nullable = false)
    private String gender;

    @Column(name = "animal_colour", nullable = false)
    private String colour;


    @Past
    @Temporal(TemporalType.DATE) // only date
    @Column(name = "animal_birth_date", nullable = false)
    private LocalDate dateOfBirth;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "animal_customer_id",referencedColumnName = "customer_id")
    private Customer customer;

    @JsonIgnore
    @OneToMany(mappedBy = "animal", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Vaccine> vaccineList;

    @JsonIgnore
    @OneToMany(mappedBy = "animal",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE )
    private List<Appointment> appointmentList;


}

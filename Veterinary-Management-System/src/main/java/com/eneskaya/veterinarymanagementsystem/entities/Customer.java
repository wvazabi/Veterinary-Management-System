package com.eneskaya.veterinarymanagementsystem.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// TODO Customer Entity
@Entity
@Table(name = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", columnDefinition = "serial")
    private long id;

    @NotNull
    @Column(name = "customer_name")
    private String name;

    @Column(name = "customer_phone")
    private String phone;

    @Column(name = "customer_mail")
    private String mail;

    @Column(name = "customer_address")
    private String address;

    @Column(name = "customer_city")
    private String city;

    @JsonIgnore
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Animal> animalList;


}

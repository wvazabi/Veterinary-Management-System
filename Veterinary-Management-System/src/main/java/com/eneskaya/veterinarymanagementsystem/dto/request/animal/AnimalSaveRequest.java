package com.eneskaya.veterinarymanagementsystem.dto.request.animal;

import com.eneskaya.veterinarymanagementsystem.entities.Customer;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalSaveRequest {

    @NotNull(message = "Animal name must not be null")
    private String name;

    @NotNull(message = "Animal species must not be null")
    private String species;

    @NotNull(message = "Animal breed must not be null")
    private String breed;

    @NotNull(message = "Animal gender must not be null")
    private String gender;

    @NotNull(message = "Animal colour must not be null")
    private String colour;

    @NotNull(message = "Animal birthdate must not be null")
    private LocalDate dateOfBirth;

    @NotNull(message = "Animal owner name must not be null")
    private Customer customer;
}

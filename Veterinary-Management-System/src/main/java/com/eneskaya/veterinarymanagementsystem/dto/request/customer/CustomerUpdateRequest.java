package com.eneskaya.veterinarymanagementsystem.dto.request.customer;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerUpdateRequest {

    @Positive(message = "ID should be positive number")
    private long id;

    @NotNull(message = "Data must not be null")
    private String name;

    private String phone;

    private String mail;

    private String address;

    private String city;
}

package com.eneskaya.veterinarymanagementsystem.dto.request.customer;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSaveRequest {
    @NotNull(message = "Data must not be null")
    private String name;

    private String phone;

    private String mail;

    private String address;

    private String city;
}

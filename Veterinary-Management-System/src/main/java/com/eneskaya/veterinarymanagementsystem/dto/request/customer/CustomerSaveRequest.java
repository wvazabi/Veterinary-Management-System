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
    @NotNull(message = "Customer name must not be null")
    private String name;

    @NotNull(message = "Customer phone must not be null")
    private String phone;

    @NotNull(message = "Customer mail must not be null")
    private String mail;

    @NotNull(message = "Customer address must not be null")
    private String address;

    @NotNull(message = "Customer city must not be null")
    private String city;
}

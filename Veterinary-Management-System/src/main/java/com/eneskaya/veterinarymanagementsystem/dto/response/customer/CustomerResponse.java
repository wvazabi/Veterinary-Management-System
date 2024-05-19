package com.eneskaya.veterinarymanagementsystem.dto.response.customer;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {

    // There is no validation because we send the information

    private int id;

    private String name;

    private String phone;

    private String mail;

    private String address;

    private String city;
}

package com.eneskaya.veterinarymanagementsystem.dto.request.doctor;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorUpdateRequest {

        @Positive(message = "Doctor id should be positive number")
        private Long id;

        @NotNull(message = "Doctor's name cannot be empty or null")
        private String name;

        @NotNull(message = "Doctor's phone cannot be empty or null")
        private String phone;

        @NotNull(message = "Doctor's email cannot be empty or null")
        private String mail;

        @NotNull(message = "Doctor's address cannot be empty or null")
        private String address;

        @NotNull(message = "Doctor's city cannot be empty or null")
        private String city;

}

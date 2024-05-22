package com.eneskaya.veterinarymanagementsystem.dto.request.availableDate;

import com.eneskaya.veterinarymanagementsystem.entities.Doctor;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateSaveRequest {

    @FutureOrPresent(message = "Available Date can not be past")
    @NotNull(message = "Available Date can't be null")
    private LocalDate availableDate;
    @NotNull(message = "Doctor ID can't be null")
    private Doctor doctor;
}

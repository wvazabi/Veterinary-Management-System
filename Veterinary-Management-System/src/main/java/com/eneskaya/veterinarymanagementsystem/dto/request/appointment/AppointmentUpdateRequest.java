package com.eneskaya.veterinarymanagementsystem.dto.request.appointment;

import com.eneskaya.veterinarymanagementsystem.entities.Animal;
import com.eneskaya.veterinarymanagementsystem.entities.Doctor;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentUpdateRequest {
    @Positive(message = "Appointment Id must be positive")
    private Long id;
    @NotNull(message = "Randevu tarihi boş veya null olamaz")
    @FutureOrPresent(message = "Appointment date can't be past")
    private LocalDateTime appointmentDate;
    @NotNull(message = "Hayvan ID'si boş veya null olamaz")
    private Animal animal;
    @NotNull(message = "Doktor ID'si boş veya null olamaz")
    private Doctor doctor;
}

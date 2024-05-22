package com.eneskaya.veterinarymanagementsystem.dto.request.report;

import com.eneskaya.veterinarymanagementsystem.entities.Appointment;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportUpdateRequest {
    private Long id;
    @NotNull(message = "Rapor başlığı boş veya null olamaz")
    private String title;
    @NotNull(message = "Tanı boş veya null olamaz")
    private String diagnosis;
    @NotNull(message = "Muayene ücreti boş veya null olamaz")
    private double price;
    @NotNull(message = "Randevu ID'si boş veya null olamaz")
    private Appointment appointment;
}

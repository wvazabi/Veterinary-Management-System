package com.eneskaya.veterinarymanagementsystem.dto.response.report;


import com.eneskaya.veterinarymanagementsystem.entities.Appointment;
import com.eneskaya.veterinarymanagementsystem.entities.Vaccine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportResponse {
    private Long id;
    private String title;
    private String diagnosis;
    private double price;
    private String animalName;
    private String customerName;
    private String doctorName;
    private Appointment appointment;
    private List<Vaccine> vaccineList;
}
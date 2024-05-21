package com.eneskaya.veterinarymanagementsystem.business.abstracts;

import com.eneskaya.veterinarymanagementsystem.dao.AppointmentRepo;
import com.eneskaya.veterinarymanagementsystem.dto.request.appointment.AppointmentSaveRequest;
import com.eneskaya.veterinarymanagementsystem.entities.Appointment;
import com.eneskaya.veterinarymanagementsystem.entities.Customer;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IAppointmentService {

    Appointment save(Appointment appointment);
    Appointment get(int id);
    //Appointment findByName(String name);
    Page<Appointment> cursor(int page, int pageSize);
    Appointment update(Appointment appointment);

    List<Appointment> appointmentListByDoctorAndDateRange(Long doctorId, LocalDate appointmentDateStart, LocalDate appointmentDateEnd);
    List<Appointment> appointmentListByAnimalAndDateRange(Long animalId, LocalDate appointmentDateStart, LocalDate appointmentDateEnd);

    boolean delete(int id);
}

package com.eneskaya.veterinarymanagementsystem.business.concretes;

import com.eneskaya.veterinarymanagementsystem.business.abstracts.IAppointmentService;
import com.eneskaya.veterinarymanagementsystem.core.exception.DoctorAppointmentException;
import com.eneskaya.veterinarymanagementsystem.core.exception.NotFoundException;
import com.eneskaya.veterinarymanagementsystem.core.result.ResultData;
import com.eneskaya.veterinarymanagementsystem.core.utilies.Msg;
import com.eneskaya.veterinarymanagementsystem.core.utilies.ResultHelper;
import com.eneskaya.veterinarymanagementsystem.dao.AnimalRepo;
import com.eneskaya.veterinarymanagementsystem.dao.AppointmentRepo;
import com.eneskaya.veterinarymanagementsystem.dao.DoctorRepo;
import com.eneskaya.veterinarymanagementsystem.dto.request.appointment.AppointmentSaveRequest;
import com.eneskaya.veterinarymanagementsystem.dto.response.appointment.AppointmentResponse;
import com.eneskaya.veterinarymanagementsystem.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AppointmentManager implements IAppointmentService {

    private final AppointmentRepo appointmentRepo;
    private final AnimalRepo animalRepo;
    private final DoctorRepo doctorRepo;

    public AppointmentManager(AppointmentRepo appointmentRepo, AnimalRepo animalRepo, DoctorRepo doctorRepo) {
        this.appointmentRepo = appointmentRepo;
        this.animalRepo = animalRepo;
        this.doctorRepo = doctorRepo;
    }

    @Override
    public Appointment save(Appointment appointment) {
        return this.appointmentRepo.save(appointment);
    }


    @Override
    public Appointment get(int id) {
        return this.appointmentRepo.findById(Long.valueOf(id)).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND_APPNTMNT));
    }

    @Override
    public Page<Appointment> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.appointmentRepo.findAll(pageable);
    }

    @Override
    public Appointment update(Appointment appointment) {
        this.get(Math.toIntExact(appointment.getId()));
        return this.appointmentRepo.save(appointment);
    }

    @Override
    public List<Appointment> appointmentListByDoctorAndDateRange(Long doctorId, LocalDate appointmentDateStart, LocalDate appointmentDateEnd) {
        LocalDateTime appointmentStart = appointmentDateStart.atStartOfDay();
        LocalDateTime appointmentEnd = appointmentDateEnd.atTime(LocalTime.MAX);
        List<Appointment> appointmentList = this.appointmentRepo.findByDoctorIdAndAppointmentDateBetween(doctorId, appointmentStart, appointmentEnd);

        if(appointmentList.isEmpty()) {
            throw new DoctorAppointmentException(doctorId + " No appointments are available for the doctor with the specified ID within the given time range.");
        }

        return appointmentList;
    }


    @Override
    public List<Appointment> appointmentListByAnimalAndDateRange(Long animalId, LocalDate appointmentDateStart, LocalDate appointmentDateEnd) {
        LocalDateTime appointmentStart = appointmentDateStart.atStartOfDay();
        LocalDateTime appointmentEnd = appointmentDateEnd.atTime(LocalTime.MAX);
        List<Appointment> appointmentList = this.appointmentRepo.findByAnimalIdAndAppointmentDateBetween(animalId, appointmentStart, appointmentEnd);

        if(appointmentList.isEmpty()) {
            throw new DoctorAppointmentException(animalId + "No appointments are available for the animal with the specified ID within the given time range");
        }

        return appointmentList;
    }


    @Override
    public boolean delete(int id) {
        Appointment appointment = this.get(id);
        this.appointmentRepo.delete(appointment);
        return true;
    }
}

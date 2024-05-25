package com.eneskaya.veterinarymanagementsystem.dao;

import com.eneskaya.veterinarymanagementsystem.entities.Animal;
import com.eneskaya.veterinarymanagementsystem.entities.Appointment;
import com.eneskaya.veterinarymanagementsystem.entities.AvailableDate;
import com.eneskaya.veterinarymanagementsystem.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long> {

    // Find an appointment by appointment date and doctor ID
    Optional<Appointment> findByAppointmentDateAndDoctorId(LocalDateTime appointmentDate, Long doctorId);

    // Find available dates for a doctor by doctor ID
    @Query("SELECT ad FROM AvailableDate ad WHERE ad.doctor.id = :id")
    List<AvailableDate> findAvailableDateByDoctorId(@Param("id") Long id);

    // Find a doctor by their ID
    @Query("FROM Doctor d WHERE d.id = :id")
    Optional<Doctor> findDoctorByDoctorId(@Param("id") Long id);

    // Find an animal by its ID
    @Query("FROM Animal a WHERE a.id = :id")
    Optional<Animal> findAnimalByAnimalId(@Param("id") Long id);

    // Find appointments for a doctor within a date range
    List<Appointment> findByDoctorIdAndAppointmentDateBetween(Long doctorId, LocalDateTime appointmentDateStart, LocalDateTime appointmentDateEnd);

    // Find appointments for an animal within a date range
    List<Appointment> findByAnimalIdAndAppointmentDateBetween(Long animalId, LocalDateTime appointmentDateStart, LocalDateTime appointmentDateEnd);
}


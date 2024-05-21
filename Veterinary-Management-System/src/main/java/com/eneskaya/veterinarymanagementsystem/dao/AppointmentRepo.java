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
    Optional<Appointment> findByAppointmentDateAndDoctorId(LocalDateTime AppointmentDate, Long doctorId);
    @Query("SELECT availableDateList adl FROM Doctor d WHERE d.id = :id")
    List<AvailableDate> findAvailableDateByDoctorId(@Param("id") Long id);
    @Query("FROM Doctor d WHERE d.id = :id")
    Optional<Doctor> findDoctorByDoctorId(@Param("id") Long id);
    @Query("FROM Animal a WHERE a.id = :id")
    Optional<Animal> findAnimalByAnimalId(@Param("id") Long id);
    List<Appointment> findByDoctorIdAndAppointmentDateBetween(Long doctorId, LocalDateTime AppointmentDateStart, LocalDateTime AppointmentDateEnd);
    List<Appointment> findByAnimalIdAndAppointmentDateBetween(Long animalId, LocalDateTime AppointmentDateStart, LocalDateTime AppointmentDateEnd);

}

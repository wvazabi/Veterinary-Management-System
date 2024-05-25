package com.eneskaya.veterinarymanagementsystem.dao;

import com.eneskaya.veterinarymanagementsystem.entities.AvailableDate;
import com.eneskaya.veterinarymanagementsystem.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AvailableDateRepo extends JpaRepository<AvailableDate, Long> {

    // Find an available date by date and doctor ID
    Optional<AvailableDate> findByAvailableDateAndDoctorId(LocalDate availableDate, Long doctorId);

    // Find a doctor by their ID
    @Query("FROM Doctor d WHERE d.id = :id")
    Optional<Doctor> findDoctorByDoctorId(@Param("id") Long id);
}

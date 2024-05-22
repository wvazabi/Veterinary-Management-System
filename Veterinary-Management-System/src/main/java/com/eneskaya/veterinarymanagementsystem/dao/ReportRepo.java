package com.eneskaya.veterinarymanagementsystem.dao;

import com.eneskaya.veterinarymanagementsystem.entities.Appointment;
import com.eneskaya.veterinarymanagementsystem.entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportRepo extends JpaRepository<Report,Long> {

    @Query("FROM Appointment a WHERE a.id = :id")
    Optional<Appointment> findAppointmentByAppointmentId(@Param("id") Long id);

}

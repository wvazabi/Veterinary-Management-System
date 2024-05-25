package com.eneskaya.veterinarymanagementsystem.dao;

import com.eneskaya.veterinarymanagementsystem.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Long> {

    // Find a doctor by name and phone
    Optional<Doctor> findByNameAndPhone(String name, String phone);
}

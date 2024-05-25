package com.eneskaya.veterinarymanagementsystem.dao;

import com.eneskaya.veterinarymanagementsystem.entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VaccineRepo extends JpaRepository<Vaccine, Long> {

    // Find vaccines by animal ID
    List<Vaccine> findByAnimalId(Long animalId);

    // Find vaccines by code and animal ID
    List<Vaccine> findByCodeAndAnimalId(String code, Long animalId);

    // Find vaccines by protection finish date between two dates
    List<Vaccine> findByProtectionFinishDateBetween(LocalDate startDate, LocalDate endDate);
}

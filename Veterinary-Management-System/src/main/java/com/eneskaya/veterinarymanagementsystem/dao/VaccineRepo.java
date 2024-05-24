package com.eneskaya.veterinarymanagementsystem.dao;

import com.eneskaya.veterinarymanagementsystem.entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VaccineRepo extends JpaRepository<Vaccine, Long> {
    List<Vaccine> findByAnimalId(Long animalId);
    List<Vaccine> findByCodeAndAnimalId(String code, Long animalId);

    List<Vaccine> findByProtectionFinishDateBetween(LocalDate startDate, LocalDate endDate);


}

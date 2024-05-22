package com.eneskaya.veterinarymanagementsystem.business.abstracts;

import com.eneskaya.veterinarymanagementsystem.core.result.ResultData;
import com.eneskaya.veterinarymanagementsystem.dto.request.vaccine.VaccineSaveRequest;
import com.eneskaya.veterinarymanagementsystem.dto.request.vaccine.VaccineUpdateRequest;
import com.eneskaya.veterinarymanagementsystem.dto.response.vaccine.VaccineResponse;
import com.eneskaya.veterinarymanagementsystem.entities.Vaccine;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IVaccineService {

    Vaccine save(VaccineSaveRequest request);
    Optional<Vaccine> get(Long id);
    Page<Vaccine> cursor(int page, int pageSize);
    Vaccine update(VaccineUpdateRequest request);
    boolean delete(Long id);

    ResultData<List<VaccineResponse>> vaccineList(LocalDate startDate, LocalDate endDate);

    ResultData<List<VaccineResponse>> findByAnimalId(Long animalID);
}

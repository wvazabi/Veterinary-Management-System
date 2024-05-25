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

    /**
     * Saves a vaccine.
     * @param request The request containing information to save the vaccine.
     * @return The result data containing the saved vaccine response.
     */
    ResultData<VaccineResponse> save(VaccineSaveRequest request);

    /**
     * Retrieves a vaccine by its ID.
     * @param id The ID of the vaccine to retrieve.
     * @return An optional containing the retrieved vaccine, if present.
     */
    Optional<Vaccine> get(Long id);

    /**
     * Retrieves a paginated list of vaccines.
     * @param page The page number to retrieve.
     * @param pageSize The number of vaccines per page.
     * @return A page of vaccines.
     */
    Page<Vaccine> cursor(int page, int pageSize);

    /**
     * Updates an existing vaccine.
     * @param id The ID of the vaccine to update.
     * @param request The request containing information to update the vaccine.
     * @return The result data containing the updated vaccine response.
     */
    ResultData<VaccineResponse> update(Long id, VaccineUpdateRequest request);

    /**
     * Deletes a vaccine by its ID.
     * @param id The ID of the vaccine to delete.
     * @return True if the deletion was successful, false otherwise.
     */
    boolean delete(Long id);

    /**
     * Retrieves vaccines with protection finish dates within a date range.
     * @param startDate The start date of the range.
     * @param endDate The end date of the range.
     * @return A list of vaccines matching the criteria.
     */
    List<Vaccine> getVaccinesByProtectionFinishDateRange(LocalDate startDate, LocalDate endDate);

    /**
     * Retrieves a list of vaccines within a date range.
     * @param startDate The start date of the range.
     * @param endDate The end date of the range.
     * @return The result data containing a list of vaccine responses.
     */
    ResultData<List<VaccineResponse>> vaccineList(LocalDate startDate, LocalDate endDate);

    /**
     * Retrieves vaccines associated with a specific animal.
     * @param animalID The ID of the animal.
     * @return The result data containing a list of vaccine responses.
     */
    ResultData<List<VaccineResponse>> findByAnimalId(Long animalID);
}

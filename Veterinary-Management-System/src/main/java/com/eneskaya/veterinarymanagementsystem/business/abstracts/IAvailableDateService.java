package com.eneskaya.veterinarymanagementsystem.business.abstracts;

import com.eneskaya.veterinarymanagementsystem.dto.request.availableDate.AvailableDateSaveRequest;
import com.eneskaya.veterinarymanagementsystem.dto.request.availableDate.AvailableDateUpdateRequest;
import com.eneskaya.veterinarymanagementsystem.entities.AvailableDate;
import com.eneskaya.veterinarymanagementsystem.entities.Customer;
import org.springframework.data.domain.Page;

public interface IAvailableDateService {

    /**
     * Saves an available date.
     * @param request The request containing information to save the available date.
     * @return The saved available date.
     */
    AvailableDate save(AvailableDateSaveRequest request);

    /**
     * Retrieves an available date by its ID.
     * @param id The ID of the available date to retrieve.
     * @return The retrieved available date.
     */
    AvailableDate get(Long id);

    /**
     * Retrieves a paginated list of available dates.
     * @param page The page number to retrieve.
     * @param pageSize The number of available dates per page.
     * @return A page of available dates.
     */
    Page<AvailableDate> cursor(int page, int pageSize);

    /**
     * Updates an existing available date.
     * @param request The request containing information to update the available date.
     * @return The updated available date.
     */
    AvailableDate update(AvailableDateUpdateRequest request);

    /**
     * Deletes an available date by its ID.
     * @param id The ID of the available date to delete.
     * @return True if the deletion was successful, false otherwise.
     */
    boolean delete(Long id);
}

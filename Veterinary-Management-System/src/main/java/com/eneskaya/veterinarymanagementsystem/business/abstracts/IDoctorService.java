package com.eneskaya.veterinarymanagementsystem.business.abstracts;

import com.eneskaya.veterinarymanagementsystem.entities.Doctor;
import org.springframework.data.domain.Page;

public interface IDoctorService {

    /**
     * Saves a doctor.
     * @param doctor The doctor to be saved.
     * @return The saved doctor.
     */
    Doctor save(Doctor doctor);

    /**
     * Retrieves a doctor by its ID.
     * @param id The ID of the doctor to retrieve.
     * @return The retrieved doctor.
     */
    Doctor get(int id);

    /**
     * Retrieves a paginated list of doctors.
     * @param page The page number to retrieve.
     * @param pageSize The number of doctors per page.
     * @return A page of doctors.
     */
    Page<Doctor> cursor(int page, int pageSize);

    /**
     * Updates an existing doctor.
     * @param doctor The doctor with updated information.
     * @return The updated doctor.
     */
    Doctor update(Doctor doctor);

    /**
     * Deletes a doctor by its ID.
     * @param id The ID of the doctor to delete.
     * @return True if the deletion was successful, false otherwise.
     */
    boolean delete(int id);
}

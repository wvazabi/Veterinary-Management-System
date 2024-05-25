package com.eneskaya.veterinarymanagementsystem.business.abstracts;

import com.eneskaya.veterinarymanagementsystem.dao.AppointmentRepo;
import com.eneskaya.veterinarymanagementsystem.dto.request.appointment.AppointmentSaveRequest;
import com.eneskaya.veterinarymanagementsystem.entities.Appointment;
import com.eneskaya.veterinarymanagementsystem.entities.Customer;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
public interface IAppointmentService {

    /**
     * Saves an appointment.
     * @param appointment The appointment to be saved.
     * @return The saved appointment.
     */
    Appointment save(Appointment appointment);

    /**
     * Retrieves an appointment by its ID.
     * @param id The ID of the appointment to retrieve.
     * @return The retrieved appointment.
     */
    Appointment get(int id);

    /**
     * Retrieves a paginated list of appointments.
     * @param page The page number to retrieve.
     * @param pageSize The number of appointments per page.
     * @return A page of appointments.
     */
    Page<Appointment> cursor(int page, int pageSize);

    /**
     * Updates an existing appointment.
     * @param appointment The appointment with updated information.
     * @return The updated appointment.
     */
    Appointment update(Appointment appointment);

    /**
     * Retrieves a list of appointments for a specific doctor within a date range.
     * @param doctorId The ID of the doctor.
     * @param appointmentDateStart The start date of the range.
     * @param appointmentDateEnd The end date of the range.
     * @return A list of appointments matching the criteria.
     */
    List<Appointment> appointmentListByDoctorAndDateRange(Long doctorId, LocalDate appointmentDateStart, LocalDate appointmentDateEnd);

    /**
     * Retrieves a list of appointments for a specific animal within a date range.
     * @param animalId The ID of the animal.
     * @param appointmentDateStart The start date of the range.
     * @param appointmentDateEnd The end date of the range.
     * @return A list of appointments matching the criteria.
     */
    List<Appointment> appointmentListByAnimalAndDateRange(Long animalId, LocalDate appointmentDateStart, LocalDate appointmentDateEnd);

    /**
     * Deletes an appointment by its ID.
     * @param id The ID of the appointment to delete.
     * @return True if the deletion was successful, false otherwise.
     */
    boolean delete(int id);
}


package com.eneskaya.veterinarymanagementsystem.business.abstracts;

import com.eneskaya.veterinarymanagementsystem.entities.Customer;
import org.springframework.data.domain.Page;

public interface ICustomerService {

    /**
     * Saves a customer.
     * @param customer The customer to be saved.
     * @return The saved customer.
     */
    Customer save(Customer customer);

    /**
     * Retrieves a customer by its ID.
     * @param id The ID of the customer to retrieve.
     * @return The retrieved customer.
     */
    Customer get(int id);

    /**
     * Finds a customer by its name.
     * @param name The name of the customer to find.
     * @return The found customer.
     */
    Customer findByName(String name);

    /**
     * Retrieves a paginated list of customers.
     * @param page The page number to retrieve.
     * @param pageSize The number of customers per page.
     * @return A page of customers.
     */
    Page<Customer> cursor(int page, int pageSize);

    /**
     * Updates an existing customer.
     * @param customer The customer with updated information.
     * @return The updated customer.
     */
    Customer update(Customer customer);

    /**
     * Deletes a customer by its ID.
     * @param id The ID of the customer to delete.
     * @return True if the deletion was successful, false otherwise.
     */
    boolean delete(int id);
}

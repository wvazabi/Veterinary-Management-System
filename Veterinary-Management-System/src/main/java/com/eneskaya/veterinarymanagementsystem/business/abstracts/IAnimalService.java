package com.eneskaya.veterinarymanagementsystem.business.abstracts;

import com.eneskaya.veterinarymanagementsystem.entities.Animal;
import com.eneskaya.veterinarymanagementsystem.entities.Customer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IAnimalService {

    /**
     * Saves an animal.
     * @param animal The animal to be saved.
     * @return The saved animal.
     */
    Animal save(Animal animal);

    /**
     * Retrieves an animal by its ID.
     * @param id The ID of the animal to retrieve.
     * @return The retrieved animal.
     */
    Animal get(int id);

    /**
     * Finds an animal by its name.
     * @param name The name of the animal to find.
     * @return The found animal.
     */
    Animal findByName(String name);

    /**
     * Retrieves a paginated list of animals.
     * @param page The page number to retrieve.
     * @param pageSize The number of animals per page.
     * @return A page of animals.
     */
    Page<Animal> cursor(int page, int pageSize);

    /**
     * Updates an existing animal.
     * @param animal The animal with updated information.
     * @return The updated animal.
     */
    Animal update(Animal animal);

    /**
     * Deletes an animal by its ID.
     * @param id The ID of the animal to delete.
     * @return True if the deletion was successful, false otherwise.
     */
    boolean delete(int id);

    /**
     * Retrieves a list of animals by a customer's name.
     * @param customerName The name of the customer whose animals to retrieve.
     * @return A list of animals belonging to the specified customer.
     */
    List<Animal> getAnimalsByCustomerName(String customerName);
}


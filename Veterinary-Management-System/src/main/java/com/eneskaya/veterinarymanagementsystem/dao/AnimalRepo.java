package com.eneskaya.veterinarymanagementsystem.dao;

import com.eneskaya.veterinarymanagementsystem.entities.Animal;
import com.eneskaya.veterinarymanagementsystem.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AnimalRepo extends JpaRepository<Animal, Integer> {

    // Find an animal by its name
    Optional<Animal> findByName(String name);

    // Custom query to find a customer by their ID
    @Query("FROM Customer c WHERE c.id = :id")
    Optional<Customer> findCustomerByCustomerId(@Param("id") Long id);

    // Find an animal by its name, species, and breed
    Optional<Animal> findByNameAndSpeciesAndBreed(String name, String species, String breed);

    // Find animals whose customer's name contains the specified value
    List<Animal> findByCustomerNameContaining(String customerName);
}

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
public interface AnimalRepo extends JpaRepository<Animal,Integer> {
    Optional<Animal> findByName(String name);

    @Query("FROM Customer c WHERE c.id = :id")
    Optional<Customer> findCustomerByCustomerId(@Param("id") Long id);

    Optional<Animal> findByNameAndSpeciesAndBreed(String name, String species, String breed);

    List<Animal> findByCustomerNameContaining(String customerName);

}

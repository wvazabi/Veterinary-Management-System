package com.eneskaya.veterinarymanagementsystem.dao;

import com.eneskaya.veterinarymanagementsystem.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

    // Find a customer by name
    Optional<Customer> findByName(String name);

    // Find a customer by name and phone
    Optional<Customer> findByNameAndPhone(String name, String phone);
}

package com.eneskaya.veterinarymanagementsystem.business.concretes;

import com.eneskaya.veterinarymanagementsystem.business.abstracts.ICustomerService;
import com.eneskaya.veterinarymanagementsystem.core.exception.NotFoundException;
import com.eneskaya.veterinarymanagementsystem.core.result.ResultData;
import com.eneskaya.veterinarymanagementsystem.core.utilies.Msg;
import com.eneskaya.veterinarymanagementsystem.dao.CustomerRepo;
import com.eneskaya.veterinarymanagementsystem.dto.response.animal.AnimalResponse;
import com.eneskaya.veterinarymanagementsystem.entities.Animal;
import com.eneskaya.veterinarymanagementsystem.entities.Customer;
import jdk.jfr.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerManager implements ICustomerService {

    private final CustomerRepo customerRepo;

    public CustomerManager(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public Customer save(Customer customer) {
        // Check if the customer already exists
        Optional<Customer> isCustomerExist = this.customerRepo.findByNameAndPhone(customer.getName(), customer.getPhone());
        if (isCustomerExist.isEmpty()) {
            // Save the customer if not already present
            return this.customerRepo.save(customer);
        }
        // Throw an exception if the customer already exists
        throw new NotFoundException(Msg.NOT_FOUND_CSTMR);
    }

    @Override
    public Customer get(int id) {
        // Retrieve a customer by ID or throw a NotFoundException
        return this.customerRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND_CSTMR));
    }

    @Override
    public Customer findByName(String name) {
        // Retrieve a customer by name or throw a NotFoundException
        return this.customerRepo.findByName(name).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Customer> cursor(int page, int pageSize) {
        // Retrieve a paginated list of customers
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.customerRepo.findAll(pageable);
    }

    @Override
    public Customer update(Customer customer) {
        // Ensure the customer exists, then update
        this.get((int) customer.getId());
        return this.customerRepo.save(customer);
    }

    @Override
    public boolean delete(int id) {
        // Delete a customer by ID after ensuring its existence
        Customer customer = this.get(id);
        this.customerRepo.delete(customer);
        return true;
    }
}


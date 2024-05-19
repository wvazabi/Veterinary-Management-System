package com.eneskaya.veterinarymanagementsystem.business.concretes;

import com.eneskaya.veterinarymanagementsystem.business.abstracts.ICustomerService;
import com.eneskaya.veterinarymanagementsystem.core.utilies.Msg;
import com.eneskaya.veterinarymanagementsystem.dao.CustomerRepo;
import com.eneskaya.veterinarymanagementsystem.entities.Customer;
import jdk.jfr.Category;
import org.springframework.stereotype.Service;

@Service
public class CustomerManager implements ICustomerService {

    private final CustomerRepo customerRepo;

    public CustomerManager(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public Customer save(Customer customer) {
        return this.customerRepo.save(customer);
    }

    @Override
    public Customer get(int id) {
        return this.customerRepo.findById(id).orElseThrow();
    }
}

package com.eneskaya.veterinarymanagementsystem.business.abstracts;

import com.eneskaya.veterinarymanagementsystem.entities.Customer;
import org.springframework.data.domain.Page;

public interface ICustomerService {
    Customer save(Customer customer);
    Customer get(int id);

    Customer findByName(String name);
    Page<Customer> cursor(int page, int pageSize);
    Customer update(Customer customer);
    boolean delete(int id);
}

package com.eneskaya.veterinarymanagementsystem.business.abstracts;

import com.eneskaya.veterinarymanagementsystem.entities.Customer;

public interface ICustomerService {
    Customer save(Customer customer);
    Customer get(int id);
}

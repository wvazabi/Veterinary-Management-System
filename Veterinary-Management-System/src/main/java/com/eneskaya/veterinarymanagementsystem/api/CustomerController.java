package com.eneskaya.veterinarymanagementsystem.api;

import com.eneskaya.veterinarymanagementsystem.business.abstracts.ICustomerService;
import com.eneskaya.veterinarymanagementsystem.entities.Customer;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/customers")
public class CustomerController {

    // Create object from interface because use less dependency
    private final ICustomerService customerService;

    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }



    // Use request,response dto we can change easily request response
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Customer save(@Valid @RequestBody Customer customer) {
        return this.customerService.save(customer);
    }

}

package com.eneskaya.veterinarymanagementsystem.api;

import com.eneskaya.veterinarymanagementsystem.business.abstracts.ICustomerService;
import com.eneskaya.veterinarymanagementsystem.core.config.modelMapper.IModelMapperService;
import com.eneskaya.veterinarymanagementsystem.core.result.Result;
import com.eneskaya.veterinarymanagementsystem.core.result.ResultData;
import com.eneskaya.veterinarymanagementsystem.core.utilies.ResultHelper;
import com.eneskaya.veterinarymanagementsystem.dto.request.customer.CustomerSaveRequest;
import com.eneskaya.veterinarymanagementsystem.dto.request.customer.CustomerUpdateRequest;
import com.eneskaya.veterinarymanagementsystem.dto.response.cursor.CursorResponse;
import com.eneskaya.veterinarymanagementsystem.dto.response.customer.CustomerResponse;
import com.eneskaya.veterinarymanagementsystem.entities.Customer;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/customers")
public class CustomerController {

    // Create object from interface because use less dependency
    private final ICustomerService customerService;
    private final IModelMapperService modelMapper;

    public CustomerController(ICustomerService customerService, IModelMapperService modelMapper) {
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }



    // Use request,response dto we can change easily request response
    // Use model mapper set for response
    // Customer Save Request transfer to customer
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CustomerResponse> save(@Valid @RequestBody CustomerSaveRequest customerSaveRequest) {
        Customer saveCustomer  = this.modelMapper.forRequest().map(customerSaveRequest,Customer.class);
        this.customerService.save(saveCustomer);

        CustomerResponse customerResponse = this.modelMapper.forResponse().map(saveCustomer,CustomerResponse.class);
        // create and return ResultData
        //return new ResultData<>(true,"Data Added","201",customerResponse);
        return ResultHelper.createData(customerResponse);
    }

    //Get by id customer
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> get(@PathVariable("id") int id) {
        Customer customer = this.customerService.get(id);
        CustomerResponse customerResponse = this.modelMapper.forResponse().map(customer, CustomerResponse.class);
        return ResultHelper.successData(customerResponse);
    }

    // get by name

    @GetMapping("/name={name}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> getByName(@PathVariable("name") String name) {
        Customer customer = this.customerService.findByName(name);
        CustomerResponse customerResponse = this.modelMapper.forResponse().map(customer, CustomerResponse.class);
        return ResultHelper.successData(customerResponse);
    }

    // find all customer with pagination
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<CustomerResponse>> cursor(
          @RequestParam(name = "page", required = false, defaultValue = "0") int page,
          @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize

    ) {

        Page<Customer> customerPage = this.customerService.cursor(page, pageSize);
        Page<CustomerResponse> customerResponsePage = customerPage
                .map(customer -> this.modelMapper.forResponse().map(customer, CustomerResponse.class));
        return ResultHelper.cursor(customerResponsePage);
    }



    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> update(@Valid @RequestBody CustomerUpdateRequest customerUpdateRequest) {
        Customer updateCustomer = this.modelMapper.forRequest().map(customerUpdateRequest, Customer.class);
        this.customerService.update(updateCustomer);
        CustomerResponse customerResponse = this.modelMapper.forResponse().map(updateCustomer, CustomerResponse.class);
        return ResultHelper.successData(customerResponse);
    }

    // Delete Operatio
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.customerService.delete(id);
        return ResultHelper.ok();

    }

}

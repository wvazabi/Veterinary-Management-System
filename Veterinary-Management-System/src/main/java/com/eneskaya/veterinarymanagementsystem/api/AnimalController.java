package com.eneskaya.veterinarymanagementsystem.api;

import com.eneskaya.veterinarymanagementsystem.business.abstracts.IAnimalService;
import com.eneskaya.veterinarymanagementsystem.business.abstracts.ICustomerService;
import com.eneskaya.veterinarymanagementsystem.core.config.modelMapper.IModelMapperService;
import com.eneskaya.veterinarymanagementsystem.core.result.Result;
import com.eneskaya.veterinarymanagementsystem.core.result.ResultData;
import com.eneskaya.veterinarymanagementsystem.core.utilies.ResultHelper;
import com.eneskaya.veterinarymanagementsystem.dto.request.animal.AnimalSaveRequest;
import com.eneskaya.veterinarymanagementsystem.dto.request.animal.AnimalUpdateRequest;
import com.eneskaya.veterinarymanagementsystem.dto.request.customer.CustomerUpdateRequest;
import com.eneskaya.veterinarymanagementsystem.dto.response.animal.AnimalResponse;
import com.eneskaya.veterinarymanagementsystem.dto.response.cursor.CursorResponse;
import com.eneskaya.veterinarymanagementsystem.dto.response.customer.CustomerResponse;
import com.eneskaya.veterinarymanagementsystem.entities.Animal;
import com.eneskaya.veterinarymanagementsystem.entities.Customer;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/animals")
public class AnimalController {

    // TODO constructor DI
    private final IAnimalService animalService;
    private final ICustomerService customerService;
    private final IModelMapperService modelMapper;

    public AnimalController(IAnimalService animalService, ICustomerService customerService, IModelMapperService modelMapper) {
        this.animalService = animalService;
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AnimalResponse> save(@Valid @RequestBody AnimalSaveRequest animalSaveRequest) {
        Long customerId = animalSaveRequest.getCustomer().getId();
        Customer customer = customerService.get(Math.toIntExact(customerId));
        Animal saveAnimal = this.modelMapper.forResponse().map(animalSaveRequest, Animal.class);
        saveAnimal.setCustomer(customer);
        this.animalService.save(saveAnimal);
        AnimalResponse animalResponse = this.modelMapper.forResponse().map(saveAnimal, AnimalResponse.class);
        animalResponse.setCustomer(customer);
        return ResultHelper.createData(animalResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> get(@PathVariable("id") Long id) {
        Animal animal = this.animalService.get(Math.toIntExact(id));
        AnimalResponse animalResponse = this.modelMapper.forResponse().map(animal, AnimalResponse.class);
        return ResultHelper.successData(animalResponse);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AnimalResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        Page<Animal> animalPage = this.animalService.cursor(page, pageSize);
        Page<AnimalResponse> animalResponsePage = animalPage
                .map(animal -> this.modelMapper.forResponse().map(animal, AnimalResponse.class));
        return ResultHelper.cursor(animalResponsePage);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.animalService.delete(id);
        return ResultHelper.ok();
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> update(@Valid @RequestBody AnimalUpdateRequest animalUpdateRequest) {
        Animal updateAnimal = this.modelMapper.forRequest().map(animalUpdateRequest, Animal.class);
        Animal updatedAnimal = this.animalService.update(updateAnimal);
        updatedAnimal.setCustomer(customerService.get((int) updatedAnimal.getCustomer().getId()));
        AnimalResponse animalResponse = this.modelMapper.forResponse().map(updatedAnimal, AnimalResponse.class);
        return ResultHelper.successData(animalResponse);
    }


    @GetMapping("/name={name}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> getByName(@PathVariable("name") String name) {
        Animal animal = this.animalService.findByName(name);
        AnimalResponse animalResponse = this.modelMapper.forResponse().map(animal,AnimalResponse.class);
        return ResultHelper.successData(animalResponse);
    }

    @GetMapping("/customer_name={customer_name}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalResponse>> getAnimalsByCustomerName(@PathVariable("customer_name") String customerName) {
        List<Animal>  animalList = this.animalService.getAnimalsByCustomerName(customerName);
        List<AnimalResponse> animalResponseList = animalList.stream().map(animal -> this.modelMapper.
                forResponse().map(animal,AnimalResponse.class)).collect(Collectors.toList());

        return ResultHelper.successData(animalResponseList);
    }






}

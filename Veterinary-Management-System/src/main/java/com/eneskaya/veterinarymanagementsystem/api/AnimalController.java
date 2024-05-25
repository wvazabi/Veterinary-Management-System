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

    // Services injected through constructor dependency injection
    private final IAnimalService animalService;
    private final ICustomerService customerService;
    private final IModelMapperService modelMapper;

    // Constructor for dependency injection
    public AnimalController(IAnimalService animalService, ICustomerService customerService, IModelMapperService modelMapper) {
        this.animalService = animalService;
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }

    // Endpoint to save a new animal
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AnimalResponse> save(@Valid @RequestBody AnimalSaveRequest animalSaveRequest) {
        Long customerId = animalSaveRequest.getCustomer().getId();
        Customer customer = customerService.get(Math.toIntExact(customerId));
        Animal saveAnimal = modelMapper.forRequest().map(animalSaveRequest, Animal.class);
        saveAnimal.setCustomer(customer);
        animalService.save(saveAnimal);
        AnimalResponse animalResponse = modelMapper.forResponse().map(saveAnimal, AnimalResponse.class);
        animalResponse.setCustomer(customer);
        return ResultHelper.createData(animalResponse);
    }

    // Endpoint to get an animal by its ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> get(@PathVariable("id") Long id) {
        Animal animal = animalService.get(Math.toIntExact(id));
        AnimalResponse animalResponse = modelMapper.forResponse().map(animal, AnimalResponse.class);
        return ResultHelper.successData(animalResponse);
    }

    // Endpoint to get a paginated list of animals
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AnimalResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        Page<Animal> animalPage = animalService.cursor(page, pageSize);
        Page<AnimalResponse> animalResponsePage = animalPage
                .map(animal -> modelMapper.forResponse().map(animal, AnimalResponse.class));
        return ResultHelper.cursor(animalResponsePage);
    }

    // Endpoint to delete an animal by its ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        animalService.delete(id);
        return ResultHelper.ok();
    }

    // Endpoint to update an existing animal
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> update(@Valid @RequestBody AnimalUpdateRequest animalUpdateRequest) {
        Animal updateAnimal = modelMapper.forRequest().map(animalUpdateRequest, Animal.class);
        Animal updatedAnimal = animalService.update(updateAnimal);
        updatedAnimal.setCustomer(customerService.get((int) updatedAnimal.getCustomer().getId()));
        AnimalResponse animalResponse = modelMapper.forResponse().map(updatedAnimal, AnimalResponse.class);
        return ResultHelper.successData(animalResponse);
    }

    // Endpoint to get an animal by its name
    @GetMapping("/name={name}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> getByName(@PathVariable("name") String name) {
        Animal animal = animalService.findByName(name);
        AnimalResponse animalResponse = modelMapper.forResponse().map(animal, AnimalResponse.class);
        return ResultHelper.successData(animalResponse);
    }

    // Endpoint to get a list of animals by customer name
    @GetMapping("/customer_name={customer_name}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalResponse>> getAnimalsByCustomerName(@PathVariable("customer_name") String customerName) {
        List<Animal> animalList = animalService.getAnimalsByCustomerName(customerName);
        List<AnimalResponse> animalResponseList = animalList.stream()
                .map(animal -> modelMapper.forResponse().map(animal, AnimalResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.successData(animalResponseList);
    }
}



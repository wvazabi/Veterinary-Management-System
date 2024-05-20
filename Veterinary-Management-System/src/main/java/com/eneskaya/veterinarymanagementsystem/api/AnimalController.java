package com.eneskaya.veterinarymanagementsystem.api;

import com.eneskaya.veterinarymanagementsystem.business.abstracts.IAnimalService;
import com.eneskaya.veterinarymanagementsystem.business.abstracts.ICustomerService;
import com.eneskaya.veterinarymanagementsystem.core.config.modelMapper.IModelMapperService;
import com.eneskaya.veterinarymanagementsystem.core.result.ResultData;
import com.eneskaya.veterinarymanagementsystem.core.utilies.ResultHelper;
import com.eneskaya.veterinarymanagementsystem.dto.request.animal.AnimalSaveRequest;
import com.eneskaya.veterinarymanagementsystem.dto.response.animal.AnimalResponse;
import com.eneskaya.veterinarymanagementsystem.dto.response.customer.CustomerResponse;
import com.eneskaya.veterinarymanagementsystem.entities.Animal;
import com.eneskaya.veterinarymanagementsystem.entities.Customer;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
        // Müşteri kimliğini al
        Long customerId = animalSaveRequest.getCustomer().getId();

        // Veritabanından müşteri bilgilerini al
        Customer customer = customerService.get(Math.toIntExact(customerId));

        // AnimalSaveRequest nesnesini Animal nesnesine dönüştür
        Animal saveAnimal = this.modelMapper.forResponse().map(animalSaveRequest, Animal.class);

        // Alınan müşteri bilgilerini hayvan nesnesine ekle
        saveAnimal.setCustomer(customer);

        // Hayvan nesnesini veritabanına kaydet
        this.animalService.save(saveAnimal);

        // Kaydedilen hayvan nesnesini AnimalResponse nesnesine dönüştür
        AnimalResponse animalResponse = this.modelMapper.forResponse().map(saveAnimal, AnimalResponse.class);

        // Yanıta müşteri bilgilerini ekle
        animalResponse.setCustomer(customer);

        // Yanıtı döndür
        return ResultHelper.createData(animalResponse);
    }



}

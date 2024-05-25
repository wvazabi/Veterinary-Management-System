package com.eneskaya.veterinarymanagementsystem.business.concretes;

import com.eneskaya.veterinarymanagementsystem.business.abstracts.IAnimalService;
import com.eneskaya.veterinarymanagementsystem.core.exception.CustomException;
import com.eneskaya.veterinarymanagementsystem.core.exception.NotFoundException;
import com.eneskaya.veterinarymanagementsystem.core.utilies.Msg;
import com.eneskaya.veterinarymanagementsystem.dao.AnimalRepo;
import com.eneskaya.veterinarymanagementsystem.entities.Animal;
import com.eneskaya.veterinarymanagementsystem.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalManager implements IAnimalService {

    private final AnimalRepo animalRepo;

    public AnimalManager(AnimalRepo animalRepo) {
        this.animalRepo = animalRepo;
    }

    @Override
    public Animal save(Animal animal) {
        // Check if the customer exists
        Optional<Customer> isCustomerExist = this.animalRepo.findCustomerByCustomerId(animal.getCustomer().getId());
        if (isCustomerExist.isEmpty()) {
            throw new NotFoundException(Msg.NOT_FOUND_CSTMR + " ID: " + animal.getCustomer().getId());
        } else {
            // Check if an animal with the same name, species, and breed exists
            Optional<Animal> isAnimalExist = this.animalRepo.findByNameAndSpeciesAndBreed(animal.getName(), animal.getSpecies(), animal.getBreed());
            if (isAnimalExist.isEmpty()) {
                return this.animalRepo.save(animal);
            }
            throw new CustomException(Msg.NOT_FOUND_DUPLICATE);
        }
    }

    @Override
    public Animal get(int id) {
        // Retrieve an animal by its ID or throw a NotFoundException
        return this.animalRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND_ANIMAL));
    }

    @Override
    public Animal findByName(String name) {
        // Find an animal by its name or throw a NotFoundException
        return this.animalRepo.findByName(name).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND_ANIMAL));
    }

    @Override
    public Page<Animal> cursor(int page, int pageSize) {
        // Retrieve a paginated list of animals
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.animalRepo.findAll(pageable);
    }

    @Override
    public Animal update(Animal animal) {
        // Check if the animal exists, then check if the customer exists
        this.get(Math.toIntExact(animal.getId()));
        Optional<Customer> isCustomerExist = this.animalRepo.findCustomerByCustomerId(animal.getCustomer().getId());
        if (isCustomerExist.isEmpty()) {
            throw new NotFoundException(Msg.NOT_FOUND_CSTMR + " ID: " + animal.getCustomer().getId());
        }
        return this.animalRepo.save(animal);
    }

    @Override
    public boolean delete(int id) {
        // Delete an animal by its ID
        Animal animal = this.get(id);
        this.animalRepo.delete(animal);
        return true;
    }

    @Override
    public List<Animal> getAnimalsByCustomerName(String customerName) {
        // Retrieve a list of animals by the name of the customer
        List<Animal> animalList = this.animalRepo.findByCustomerNameContaining(customerName);
        if (animalList.isEmpty()) {
            throw new NotFoundException(Msg.NOT_FOUND_CSTMR);
        }
        return animalList;
    }
}


package com.eneskaya.veterinarymanagementsystem.business.abstracts;

import com.eneskaya.veterinarymanagementsystem.entities.Animal;
import com.eneskaya.veterinarymanagementsystem.entities.Customer;
import org.springframework.data.domain.Page;

public interface IAnimalService {

    Animal save(Animal animal);
    Animal get(int id);

    Animal findByName(String name);
    Page<Animal> cursor(int page, int pageSize);
    Animal update(Animal animal);
    boolean delete(int id);

}

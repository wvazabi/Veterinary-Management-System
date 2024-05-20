package com.eneskaya.veterinarymanagementsystem.business.abstracts;

import com.eneskaya.veterinarymanagementsystem.entities.Doctor;
import org.springframework.data.domain.Page;

public interface IDoctorService {

    Doctor save(Doctor doctor);
    Doctor get(int id);

    //Doctor findByName(String name);
    Page<Doctor> cursor(int page, int pageSize);
    Doctor update(Doctor doctor);
    boolean delete(int id);

}

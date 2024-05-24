package com.eneskaya.veterinarymanagementsystem.business.concretes;


import com.eneskaya.veterinarymanagementsystem.business.abstracts.IDoctorService;
import com.eneskaya.veterinarymanagementsystem.core.exception.CustomException;
import com.eneskaya.veterinarymanagementsystem.core.exception.NotFoundException;
import com.eneskaya.veterinarymanagementsystem.core.utilies.Msg;
import com.eneskaya.veterinarymanagementsystem.dao.DoctorRepo;
import com.eneskaya.veterinarymanagementsystem.entities.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class DoctorManager implements IDoctorService {

    private final DoctorRepo doctorRepo;

    @Autowired
    public DoctorManager(DoctorRepo doctorRepo) {
        this.doctorRepo = doctorRepo;
    }

    @Override
    public Doctor save(Doctor doctor) {

        Optional<Doctor> isDoctorExist = this.doctorRepo.findByNameAndPhone(doctor.getName(), doctor.getPhone());
        if(isDoctorExist.isEmpty()) {
            return this.doctorRepo.save(doctor);
        }
        throw new CustomException(Msg.NOT_FOUND_DUPLICATE);



    }

    @Override
    public Doctor get(int id) {
        return this.doctorRepo.findById((long) id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND_DR));
    }

    @Override
    public Page<Doctor> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.doctorRepo.findAll(pageable);
    }

    @Override
    public Doctor update(Doctor doctor) {
        this.doctorRepo.findById(doctor.getId()).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND_DR));
        return this.doctorRepo.save(doctor);
    }

    @Override
    public boolean delete(int id) {
        Doctor doctor = this.get(id);
        this.doctorRepo.delete(doctor);
        return true;
    }
}


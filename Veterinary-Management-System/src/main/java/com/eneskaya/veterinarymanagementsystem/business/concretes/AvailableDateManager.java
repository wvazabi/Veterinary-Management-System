package com.eneskaya.veterinarymanagementsystem.business.concretes;

import com.eneskaya.veterinarymanagementsystem.business.abstracts.IAvailableDateService;
import com.eneskaya.veterinarymanagementsystem.core.config.modelMapper.IModelMapperService;
import com.eneskaya.veterinarymanagementsystem.core.exception.CustomException;
import com.eneskaya.veterinarymanagementsystem.core.exception.NotFoundException;
import com.eneskaya.veterinarymanagementsystem.core.utilies.Msg;
import com.eneskaya.veterinarymanagementsystem.dao.AvailableDateRepo;
import com.eneskaya.veterinarymanagementsystem.dao.DoctorRepo;
import com.eneskaya.veterinarymanagementsystem.dto.request.availableDate.AvailableDateSaveRequest;
import com.eneskaya.veterinarymanagementsystem.dto.request.availableDate.AvailableDateUpdateRequest;
import com.eneskaya.veterinarymanagementsystem.entities.AvailableDate;
import com.eneskaya.veterinarymanagementsystem.entities.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.Optional;

@Service
public class AvailableDateManager implements IAvailableDateService {

    private final AvailableDateRepo availableDateRepo;
    private final DoctorRepo doctorRepo;
    private final IModelMapperService modelMapper;

    public AvailableDateManager(AvailableDateRepo availableDateRepo, DoctorRepo doctorRepo, IModelMapperService modelMapper) {
        this.availableDateRepo = availableDateRepo;
        this.doctorRepo = doctorRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public AvailableDate save(AvailableDateSaveRequest request) {
        // Check if the doctor exists
        Optional<Doctor> isDoctorExist = this.availableDateRepo.findDoctorByDoctorId(request.getDoctor().getId());
        if (isDoctorExist.isEmpty()) {
            throw new NotFoundException(Msg.NOT_FOUND_DR + "Doctor ID" + request.getDoctor().getId());
        } else {
            // Check if the date is already taken
            Optional<AvailableDate> isAvailableDateExist = this.availableDateRepo.findByAvailableDateAndDoctorId(request.getAvailableDate(),
                    request.getDoctor().getId());
            if (isAvailableDateExist.isEmpty()) {
                // Map and save the available date
                request.setDoctor(this.doctorRepo.findById(request.getDoctor().getId()).get());
                AvailableDate saveAvailableDate = this.modelMapper.forRequest().map(request, AvailableDate.class);
                this.availableDateRepo.save(saveAvailableDate);
                return saveAvailableDate;
            }
            throw new CustomException("The date is already taken");
        }
    }

    @Override
    public AvailableDate get(Long id) {
        // Retrieve an available date by its ID or throw a NotFoundException
        return this.availableDateRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND_AVLB_DATE));
    }

    @Override
    public Page<AvailableDate> cursor(int page, int pageSize) {
        // Retrieve a paginated list of available dates
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.availableDateRepo.findAll(pageable);
    }

    @Override
    public AvailableDate update(AvailableDateUpdateRequest request) {
        // Check if the doctor exists
        Optional<Doctor> isDoctorExist = this.availableDateRepo.findDoctorByDoctorId(request.getDoctor().getId());
        if (isDoctorExist.isEmpty()) {
            throw new NotFoundException(Msg.NOT_FOUND_DR + "Doctor ID" + request.getDoctor().getId());
        } else {
            // Check if the available date exists, then update it
            AvailableDate availableDate = this.availableDateRepo.findById(request.getId()).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND_AVLB_DATE));
            Optional<AvailableDate> isAvailableDateExist = this.availableDateRepo.findByAvailableDateAndDoctorId(request.getAvailableDate(),
                    request.getDoctor().getId());
            if (isAvailableDateExist.isEmpty()) {
                request.setDoctor(this.doctorRepo.findById(request.getDoctor().getId()).get());
                AvailableDate saveAvailableDate = this.modelMapper.forRequest().map(request, AvailableDate.class);
                this.availableDateRepo.save(saveAvailableDate);
                return saveAvailableDate;
            }
            throw new CustomException("The date is already taken");
        }
    }

    @Override
    public boolean delete(Long id) {
        // Delete an available date by its ID
        AvailableDate availableDate = this.get(id);
        this.availableDateRepo.delete(availableDate);
        return true;
    }
}


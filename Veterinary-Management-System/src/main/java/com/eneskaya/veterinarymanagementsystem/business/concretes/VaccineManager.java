package com.eneskaya.veterinarymanagementsystem.business.concretes;

import com.eneskaya.veterinarymanagementsystem.business.abstracts.IVaccineService;
import com.eneskaya.veterinarymanagementsystem.core.config.modelMapper.IModelMapperService;
import com.eneskaya.veterinarymanagementsystem.core.exception.CustomException;
import com.eneskaya.veterinarymanagementsystem.core.exception.NotFoundException;
import com.eneskaya.veterinarymanagementsystem.core.result.ResultData;
import com.eneskaya.veterinarymanagementsystem.core.utilies.Msg;
import com.eneskaya.veterinarymanagementsystem.core.utilies.ResultHelper;
import com.eneskaya.veterinarymanagementsystem.dao.AnimalRepo;
import com.eneskaya.veterinarymanagementsystem.dao.VaccineRepo;
import com.eneskaya.veterinarymanagementsystem.dto.request.vaccine.VaccineSaveRequest;
import com.eneskaya.veterinarymanagementsystem.dto.request.vaccine.VaccineUpdateRequest;
import com.eneskaya.veterinarymanagementsystem.dto.response.animal.AnimalResponse;
import com.eneskaya.veterinarymanagementsystem.dto.response.vaccine.VaccineResponse;
import com.eneskaya.veterinarymanagementsystem.entities.Customer;
import com.eneskaya.veterinarymanagementsystem.entities.Vaccine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VaccineManager implements IVaccineService {

    private final VaccineRepo vaccineRepo;
    private final AnimalRepo animalRepo;
    private final IModelMapperService modelMapper;

    public VaccineManager(VaccineRepo vaccineRepo, AnimalRepo animalRepo, IModelMapperService modelMapper) {
        this.vaccineRepo = vaccineRepo;
        this.animalRepo = animalRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public Vaccine save(VaccineSaveRequest request) {
        List<Vaccine> vaccineList = this.vaccineRepo.findByCodeAndAnimalId(request.getCode(), request.getAnimal().getId());
        if(!vaccineList.isEmpty() && vaccineList.get(vaccineList.size()-1).getProtectionFinishDate().isAfter(LocalDate.now())) {
            throw new CustomException("Bu hastaya " + request.getName() + " aşısı uygulanmış ve koruyuculuk süresi devam ettiğinden sisteme yeniden girilemez!");
        } else {
            request.setAnimal(this.animalRepo.findById(Math.toIntExact(request.getAnimal().getId())).get());
            Vaccine vaccine = this.modelMapper.forResponse().map(request,Vaccine.class);
            return this.vaccineRepo.save(vaccine);
        }
    }

    @Override
    public Optional<Vaccine> get(Long id) {
        return Optional.ofNullable(this.vaccineRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND_VACCINE)));
    }

    @Override
    public Page<Vaccine> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.vaccineRepo.findAll(pageable);
    }

    @Override
    public ResultData<VaccineResponse> update(Long id, VaccineUpdateRequest request) {
        Vaccine vaccine = this.vaccineRepo.findById(request.getId()).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
         VaccineResponse vaccineResponse = this.modelMapper.forResponse().map(vaccine,VaccineResponse.class);
        this.vaccineRepo.save(vaccine);
        return ResultHelper.successData(vaccineResponse);
    }

    @Override
    public boolean delete(Long id) {
        this.vaccineRepo.delete(this.vaccineRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND)));

        return true;
    }

    @Override
    public ResultData<List<VaccineResponse>> vaccineList(LocalDate startDate, LocalDate endDate) {
        List<Vaccine> allVaccines = this.vaccineRepo.findAll();
        List<Vaccine> upcomings = new ArrayList<>();
        for(Vaccine obj : allVaccines) {
            if(startDate.isBefore(obj.getProtectionFinishDate()) && endDate.isAfter(obj.getProtectionFinishDate())) {
                upcomings.add(obj);
            }
        }
        if(upcomings.isEmpty()) {
            throw new NotFoundException("Girilen aralıkta aşı bulunamadı");
        }
        List<VaccineResponse> vaccineResponseList = upcomings.stream().map(vaccine ->   this.modelMapper.
                forResponse().map(vaccine,VaccineResponse.class)).collect(Collectors.toList());

        return ResultHelper.successData(vaccineResponseList);
    }

    @Override
    public ResultData<List<VaccineResponse>> findByAnimalId(Long animalID) {
        List<Vaccine> vaccineList = this.vaccineRepo.findByAnimalId(animalID);
        if(!vaccineList.isEmpty()) {
            List<VaccineResponse> vaccineResponseList = vaccineList.stream().map(vaccine ->   this.modelMapper.
                    forResponse().map(vaccine,VaccineResponse.class)).collect(Collectors.toList());
            return ResultHelper.successData(vaccineResponseList);
        }
        throw new NotFoundException(Msg.NOT_FOUND);
    }
}

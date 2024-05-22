package com.eneskaya.veterinarymanagementsystem.business.concretes;

import com.eneskaya.veterinarymanagementsystem.business.abstracts.IVaccineService;
import com.eneskaya.veterinarymanagementsystem.core.config.modelMapper.IModelMapperService;
import com.eneskaya.veterinarymanagementsystem.core.exception.CustomException;
import com.eneskaya.veterinarymanagementsystem.dao.AnimalRepo;
import com.eneskaya.veterinarymanagementsystem.dao.VaccineRepo;
import com.eneskaya.veterinarymanagementsystem.dto.request.vaccine.VaccineSaveRequest;
import com.eneskaya.veterinarymanagementsystem.dto.response.vaccine.VaccineResponse;
import com.eneskaya.veterinarymanagementsystem.entities.AvailableDate;
import com.eneskaya.veterinarymanagementsystem.entities.Vaccine;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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
            this.vaccineRepo.save(vaccine);
            return vaccine;
        }
    }

    @Override
    public Vaccine get(Long id) {
        return null;
    }

    @Override
    public Page<Vaccine> cursor(int page, int pageSize) {
        return null;
    }

    @Override
    public Vaccine update(Vaccine vaccine) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<Vaccine> VaccineList(LocalDate startDate, LocalDate endDate) {
        return null;
    }

    @Override
    public List<Vaccine> findByAnimalId(Long animalID) {
        return null;
    }
}

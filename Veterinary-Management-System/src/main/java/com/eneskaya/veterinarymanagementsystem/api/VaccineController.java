package com.eneskaya.veterinarymanagementsystem.api;

import com.eneskaya.veterinarymanagementsystem.business.abstracts.IAnimalService;
import com.eneskaya.veterinarymanagementsystem.business.abstracts.IVaccineService;
import com.eneskaya.veterinarymanagementsystem.core.config.modelMapper.IModelMapperService;
import com.eneskaya.veterinarymanagementsystem.core.result.Result;
import com.eneskaya.veterinarymanagementsystem.core.result.ResultData;
import com.eneskaya.veterinarymanagementsystem.core.utilies.ResultHelper;
import com.eneskaya.veterinarymanagementsystem.dto.request.vaccine.VaccineSaveRequest;
import com.eneskaya.veterinarymanagementsystem.dto.request.vaccine.VaccineUpdateRequest;
import com.eneskaya.veterinarymanagementsystem.dto.response.cursor.CursorResponse;
import com.eneskaya.veterinarymanagementsystem.dto.response.vaccine.VaccineResponse;
import com.eneskaya.veterinarymanagementsystem.entities.Animal;
import com.eneskaya.veterinarymanagementsystem.entities.Vaccine;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/vaccines")
public class VaccineController {

    private final IVaccineService vaccineService;
    private final IModelMapperService modelMapper;
    private final IAnimalService animalService;


    public VaccineController(IVaccineService vaccineService, IModelMapperService modelMapper, IAnimalService animalService) {
        this.vaccineService = vaccineService;
        this.modelMapper = modelMapper;
        this.animalService = animalService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<VaccineResponse> save(@Valid @RequestBody VaccineSaveRequest request) {
        return this.vaccineService.save(request);
    }
//    public ResultData<VaccineResponse> save(@Valid @RequestBody VaccineSaveRequest vaccineSaveRequest) {
//        Vaccine savedVaccine = vaccineService.save(vaccineSaveRequest);
//        VaccineResponse vaccineResponse = modelMapper.forResponse().map(savedVaccine, VaccineResponse.class);
//        vaccineResponse.setAnimal(savedVaccine.getAnimal());
//        return ResultHelper.createData(vaccineResponse);
//    }



    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<VaccineResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "100") int pageSize

    ) {

        Page<Vaccine> vaccinePage = this.vaccineService.cursor(page, pageSize);
        Page<VaccineResponse> vaccineResponsePage = vaccinePage
                .map(vaccine -> this.modelMapper.forResponse().map(vaccine, VaccineResponse.class));
        return ResultHelper.cursor(vaccineResponsePage);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> update(@Valid @PathVariable("id") Long id, @RequestBody VaccineUpdateRequest vaccineUpdateRequest) {
        return this.vaccineService.update(id, vaccineUpdateRequest);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.vaccineService.delete((long) id);
        return ResultHelper.ok();

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> getById(@PathVariable("id") Long id) {
        Optional<Vaccine> vaccine = this.vaccineService.get(id);
        VaccineResponse vaccineResponse = this.modelMapper.forResponse().map(vaccine,VaccineResponse.class);
        return ResultHelper.successData(vaccineResponse);
    }


    @GetMapping("/vaccine_calendar")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccineResponse>> upcomingVaccines(@RequestParam("startDate") LocalDate startDate,
                                                              @RequestParam("endDate") LocalDate endDate) {
        return this.vaccineService.vaccineList(startDate, endDate);
    }

    @GetMapping("/animalId={animalId}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccineResponse>> getByAnimalId(@PathVariable("animalId") Long animalId) {
        return this.vaccineService.findByAnimalId(animalId);
    }










}

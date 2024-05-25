package com.eneskaya.veterinarymanagementsystem.api;

import com.eneskaya.veterinarymanagementsystem.business.abstracts.IDoctorService;
import com.eneskaya.veterinarymanagementsystem.core.config.modelMapper.IModelMapperService;
import com.eneskaya.veterinarymanagementsystem.core.result.Result;
import com.eneskaya.veterinarymanagementsystem.core.result.ResultData;
import com.eneskaya.veterinarymanagementsystem.core.utilies.ResultHelper;
import com.eneskaya.veterinarymanagementsystem.dto.request.doctor.DoctorSaveRequest;
import com.eneskaya.veterinarymanagementsystem.dto.request.doctor.DoctorUpdateRequest;
import com.eneskaya.veterinarymanagementsystem.dto.response.cursor.CursorResponse;
import com.eneskaya.veterinarymanagementsystem.dto.response.doctor.DoctorResponse;
import com.eneskaya.veterinarymanagementsystem.entities.Doctor;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/doctors")
public class DoctorController {

    // Services injected through constructor dependency injection
    private final IDoctorService doctorService;
    private final IModelMapperService modelMapper;

    // Constructor for dependency injection
    public DoctorController(IDoctorService doctorService, IModelMapperService modelMapper) {
        this.doctorService = doctorService;
        this.modelMapper = modelMapper;
    }

    // Endpoint to save a new doctor
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<DoctorResponse> save(@Valid @RequestBody DoctorSaveRequest doctorSaveRequest) {
        Doctor saveDoctor = this.modelMapper.forRequest().map(doctorSaveRequest, Doctor.class);
        this.doctorService.save(saveDoctor);
        DoctorResponse doctorResponse = this.modelMapper.forResponse().map(saveDoctor, DoctorResponse.class);
        return ResultHelper.createData(doctorResponse);
    }

    // Endpoint to get a doctor by its ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<DoctorResponse> get(@PathVariable("id") int id) {
        Doctor doctor = this.doctorService.get(id);
        DoctorResponse doctorResponse = this.modelMapper.forResponse().map(doctor, DoctorResponse.class);
        return ResultHelper.successData(doctorResponse);
    }

    // Endpoint to get a paginated list of doctors
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<DoctorResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize) {

        Page<Doctor> doctorPage = this.doctorService.cursor(page, pageSize);
        Page<DoctorResponse> doctorResponsePage = doctorPage
                .map(doctor -> this.modelMapper.forResponse().map(doctor, DoctorResponse.class));
        return ResultHelper.cursor(doctorResponsePage);
    }

    // Endpoint to update an existing doctor's address
    @PutMapping("/address")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<DoctorResponse> update(@Valid @RequestBody DoctorUpdateRequest doctorUpdateRequest) {
        Doctor updateDoctor = this.modelMapper.forRequest().map(doctorUpdateRequest, Doctor.class);
        this.doctorService.update(updateDoctor);
        DoctorResponse doctorResponse = this.modelMapper.forResponse().map(updateDoctor, DoctorResponse.class);
        return ResultHelper.successData(doctorResponse);
    }

    // Endpoint to delete a doctor by its ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.doctorService.delete(id);
        return ResultHelper.ok();
    }
}


package com.eneskaya.veterinarymanagementsystem.api;

import com.eneskaya.veterinarymanagementsystem.business.abstracts.IAvailableDateService;
import com.eneskaya.veterinarymanagementsystem.business.abstracts.IDoctorService;
import com.eneskaya.veterinarymanagementsystem.core.config.modelMapper.IModelMapperService;
import com.eneskaya.veterinarymanagementsystem.core.result.Result;
import com.eneskaya.veterinarymanagementsystem.core.result.ResultData;
import com.eneskaya.veterinarymanagementsystem.core.utilies.ResultHelper;
import com.eneskaya.veterinarymanagementsystem.dto.request.animal.AnimalUpdateRequest;
import com.eneskaya.veterinarymanagementsystem.dto.request.availableDate.AvailableDateSaveRequest;
import com.eneskaya.veterinarymanagementsystem.dto.request.availableDate.AvailableDateUpdateRequest;
import com.eneskaya.veterinarymanagementsystem.dto.response.animal.AnimalResponse;
import com.eneskaya.veterinarymanagementsystem.dto.response.availableDate.AvailableDateResponse;
import com.eneskaya.veterinarymanagementsystem.dto.response.cursor.CursorResponse;
import com.eneskaya.veterinarymanagementsystem.entities.Animal;
import com.eneskaya.veterinarymanagementsystem.entities.AvailableDate;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/available_dates")
public class AvailableDateController {

    // Services injected through constructor dependency injection
    private final IAvailableDateService availableDateService;
    private final IDoctorService doctorService;
    private final IModelMapperService modelMapper;

    // Constructor for dependency injection
    public AvailableDateController(IAvailableDateService availableDateService, IDoctorService doctorService, IModelMapperService modelMapper) {
        this.availableDateService = availableDateService;
        this.doctorService = doctorService;
        this.modelMapper = modelMapper;
    }

    // Endpoint to save a new available date
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AvailableDateResponse> save(@Valid @RequestBody AvailableDateSaveRequest availableDateSaveRequest) {
        AvailableDate savedAvailableDate = this.availableDateService.save(availableDateSaveRequest);
        AvailableDateResponse availableDateResponse = this.modelMapper.forResponse().map(savedAvailableDate, AvailableDateResponse.class);
        availableDateResponse.setDoctor(savedAvailableDate.getDoctor());
        availableDateResponse.setAvailableDate(savedAvailableDate.getAvailableDate());
        return ResultHelper.createData(availableDateResponse);
    }

    // Endpoint to update an existing available date
    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> update(@Valid @RequestBody AvailableDateUpdateRequest availableDateUpdateRequest) {
        AvailableDate updatedAvailableDate = this.availableDateService.update(availableDateUpdateRequest);
        AvailableDateResponse availableDateResponse = this.modelMapper.forResponse().map(updatedAvailableDate, AvailableDateResponse.class);
        availableDateResponse.setDoctor(updatedAvailableDate.getDoctor());
        availableDateResponse.setAvailableDate(updatedAvailableDate.getAvailableDate());
        return ResultHelper.createData(availableDateResponse);
    }

    // Endpoint to get an available date by its ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> get(@PathVariable("id") Long id) {
        AvailableDate availableDate = this.availableDateService.get(id);
        AvailableDateResponse availableDateResponse = this.modelMapper.forResponse().map(availableDate, AvailableDateResponse.class);
        return ResultHelper.successData(availableDateResponse);
    }

    // Endpoint to get a paginated list of available dates
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AvailableDateResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        Page<AvailableDate> availableDatesPage = this.availableDateService.cursor(page, pageSize);
        Page<AvailableDateResponse> availableDatesResponsePage = availableDatesPage
                .map(availableDate -> this.modelMapper.forResponse().map(availableDate, AvailableDateResponse.class));
        return ResultHelper.cursor(availableDatesResponsePage);
    }

    // Endpoint to delete an available date by its ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.availableDateService.delete(id);
        return ResultHelper.ok();
    }
}


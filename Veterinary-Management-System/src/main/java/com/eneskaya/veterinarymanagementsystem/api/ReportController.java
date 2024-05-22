package com.eneskaya.veterinarymanagementsystem.api;

import com.eneskaya.veterinarymanagementsystem.business.abstracts.IReportService;
import com.eneskaya.veterinarymanagementsystem.core.config.modelMapper.IModelMapperService;
import com.eneskaya.veterinarymanagementsystem.core.result.ResultData;
import com.eneskaya.veterinarymanagementsystem.dto.request.report.ReportUpdateRequest;
import com.eneskaya.veterinarymanagementsystem.dto.response.report.ReportResponse;
import com.eneskaya.veterinarymanagementsystem.entities.Report;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/reports")
public class ReportController {

    private final IReportService reportService;
    private final IModelMapperService modelMapper;

    public ReportController(IReportService reportService, IModelMapperService modelMapper) {
        this.reportService = reportService;
        this.modelMapper = modelMapper;
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<ReportResponse>> findAll() {
        return this.reportService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<ReportResponse> findById(@PathVariable Long id) {
        return this.reportService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<ReportResponse> save(@Valid @RequestBody ReportUpdateRequest request) {
        return this.reportService.save(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<ReportResponse> update(@Valid @PathVariable Long id, @RequestBody ReportUpdateRequest request) {
        return  this.reportService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        this.reportService.delete(id);
    }


}

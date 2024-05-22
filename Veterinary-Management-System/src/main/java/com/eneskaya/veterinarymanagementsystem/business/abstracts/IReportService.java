package com.eneskaya.veterinarymanagementsystem.business.abstracts;

import com.eneskaya.veterinarymanagementsystem.core.result.ResultData;
import com.eneskaya.veterinarymanagementsystem.dto.request.report.ReportUpdateRequest;
import com.eneskaya.veterinarymanagementsystem.dto.response.report.ReportResponse;

import java.util.List;

public interface IReportService {

    ResultData<ReportResponse> getById(Long id);

    ResultData<List<ReportResponse>> findAll();

    ResultData<ReportResponse> save(ReportUpdateRequest request);


    ResultData<ReportResponse> update(Long id, ReportUpdateRequest request);

    void delete(Long id);

}

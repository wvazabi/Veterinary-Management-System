package com.eneskaya.veterinarymanagementsystem.business.concretes;

import com.eneskaya.veterinarymanagementsystem.business.abstracts.IReportService;
import com.eneskaya.veterinarymanagementsystem.core.config.modelMapper.IModelMapperService;
import com.eneskaya.veterinarymanagementsystem.core.exception.CustomException;
import com.eneskaya.veterinarymanagementsystem.core.exception.NotFoundException;
import com.eneskaya.veterinarymanagementsystem.core.result.Result;
import com.eneskaya.veterinarymanagementsystem.core.result.ResultData;
import com.eneskaya.veterinarymanagementsystem.core.utilies.Msg;
import com.eneskaya.veterinarymanagementsystem.core.utilies.ResultHelper;
import com.eneskaya.veterinarymanagementsystem.dao.AppointmentRepo;
import com.eneskaya.veterinarymanagementsystem.dao.ReportRepo;
import com.eneskaya.veterinarymanagementsystem.dto.request.report.ReportUpdateRequest;
import com.eneskaya.veterinarymanagementsystem.dto.response.report.ReportResponse;
import com.eneskaya.veterinarymanagementsystem.entities.Appointment;
import com.eneskaya.veterinarymanagementsystem.entities.Report;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportManager implements IReportService {

    private final ReportRepo reportRepo;
    private final AppointmentRepo appointmentRepo;
    private final IModelMapperService modelMapper;

    public ReportManager(ReportRepo reportRepo, AppointmentRepo appointmentRepo, IModelMapperService modelMapper) {
        this.reportRepo = reportRepo;
        this.appointmentRepo = appointmentRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResultData<ReportResponse> getById(Long id) {
        Report report = this.reportRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        ReportResponse reportResponse = this.modelMapper.forResponse().map(report,ReportResponse.class);
        return ResultHelper.successData(reportResponse);
    }

    @Override
    public ResultData<List<ReportResponse>> findAll() {

        List<Report> reportList = this.reportRepo.findAll();

        // ModelMapper ile Liste Dönüştürme
        List<ReportResponse> reportResponseList = reportList.stream()
                .map(report -> this.modelMapper.forResponse().map(report, ReportResponse.class))
                .collect(Collectors.toList());

        return ResultHelper.successData(reportResponseList);
    }

    @Override
    public ResultData<ReportResponse> save(ReportUpdateRequest request) {
        Optional<Appointment> isAppointmentExist = this.appointmentRepo.findById(request.getAppointment().getId());

        if(isAppointmentExist.isEmpty()) {
            throw new NotFoundException(request.getAppointment().getId() + " ID'li randevu bulunamadı.");
        }

        List<Report> reportList = this.reportRepo.findAll();
        for(Report report : reportList) {
            if(report.getAppointment().getId() == request.getAppointment().getId()) {
                throw new CustomException("Bu randevuya ait daha önce bir rapor oluşturulmuş!");
            }
        }

        request.setAppointment(isAppointmentExist.get());
        Report report = this.modelMapper.forRequest().map(request, Report.class);
        this.reportRepo.save(report);
        ReportResponse reportResponse = this.modelMapper.forResponse().map(report, ReportResponse.class);

        return ResultHelper.createData(reportResponse);

    }

    @Override
    public ResultData<ReportResponse> update(Long id, ReportUpdateRequest request) {

        Optional<Appointment> isAppointmentExist = this.reportRepo.findAppointmentByAppointmentId(request.getAppointment().getId());


        if(isAppointmentExist.isEmpty()) {
            throw new NotFoundException("ID'si " + request.getAppointment().getId() + " olan randevu bulunamadı");
        } else {
            Report report = this.reportRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
            this.reportRepo.save(report);
            ReportResponse reportResponse = this.modelMapper.forResponse().map(report, ReportResponse.class);

            return ResultHelper.createData(reportResponse);
        }
    }

    @Override
    public void delete(Long id) {
        reportRepo.delete(this.reportRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND)));

    }
}

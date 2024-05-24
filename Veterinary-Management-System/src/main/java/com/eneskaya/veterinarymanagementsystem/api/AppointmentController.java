package com.eneskaya.veterinarymanagementsystem.api;

import com.eneskaya.veterinarymanagementsystem.business.abstracts.IAnimalService;
import com.eneskaya.veterinarymanagementsystem.business.abstracts.IAppointmentService;
import com.eneskaya.veterinarymanagementsystem.business.abstracts.IDoctorService;
import com.eneskaya.veterinarymanagementsystem.core.config.modelMapper.IModelMapperService;
import com.eneskaya.veterinarymanagementsystem.core.result.Result;
import com.eneskaya.veterinarymanagementsystem.core.result.ResultData;

import com.eneskaya.veterinarymanagementsystem.core.utilies.ResultHelper;
import com.eneskaya.veterinarymanagementsystem.dao.AppointmentRepo;
import com.eneskaya.veterinarymanagementsystem.dto.request.animal.AnimalSaveRequest;
import com.eneskaya.veterinarymanagementsystem.dto.request.appointment.AppointmentSaveRequest;
import com.eneskaya.veterinarymanagementsystem.dto.request.appointment.AppointmentUpdateRequest;
import com.eneskaya.veterinarymanagementsystem.dto.response.animal.AnimalResponse;
import com.eneskaya.veterinarymanagementsystem.dto.response.appointment.AppointmentResponse;
import com.eneskaya.veterinarymanagementsystem.dto.response.cursor.CursorResponse;
import com.eneskaya.veterinarymanagementsystem.entities.Animal;
import com.eneskaya.veterinarymanagementsystem.entities.Appointment;
import com.eneskaya.veterinarymanagementsystem.entities.Customer;
import com.eneskaya.veterinarymanagementsystem.entities.Doctor;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/appointments")
public class AppointmentController {

    private final IAppointmentService appointmentService;
    private final IDoctorService doctorService;
    private final IAnimalService animalService;
    private final IModelMapperService modelMapper;

    public AppointmentController(IAppointmentService appointmentService, IDoctorService doctorService, IAnimalService animalService, IModelMapperService modelMapper) {
        this.appointmentService = appointmentService;
        this.doctorService = doctorService;
        this.animalService = animalService;
        this.modelMapper = modelMapper;
    }



    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AppointmentResponse> save(@Valid @RequestBody AppointmentSaveRequest appointmentSaveRequest) {
//        Long doctorId = appointmentSaveRequest.getDoctor().getId();
//        Long animalId = appointmentSaveRequest.getAnimal().getId();
//        Doctor doctor = this.doctorService.get(Math.toIntExact(doctorId));
//        Animal animal = this.animalService.get(Math.toIntExact(animalId));

        Appointment saveAppointment = this.modelMapper.forRequest().map(appointmentSaveRequest, Appointment.class);
//        saveAppointment.setAnimal(animal);
//        saveAppointment.setDoctor(doctor);
        this.appointmentService.save(saveAppointment);

        AppointmentResponse appointmentResponse = this.modelMapper.forResponse().map(saveAppointment,AppointmentResponse.class);
//        appointmentResponse.setAnimal(animal);
//        appointmentResponse.setDoctor(doctor);

        return ResultHelper.createData(appointmentResponse);
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> get(@PathVariable("id") int id) {
        Appointment appointment = this.appointmentService.get(id);
        AppointmentResponse appointmentResponse = this.modelMapper.forResponse().map(appointment,AppointmentResponse.class);
        return ResultHelper.successData(appointmentResponse);
    }


    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AppointmentResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ) {
        Page<Appointment> appointmentPage = this.appointmentService.cursor(page, pageSize);
        Page<AppointmentResponse> appointmentResponsePage = appointmentPage
                .map(appointment -> this.modelMapper.forResponse().map(appointment, AppointmentResponse.class));
        return ResultHelper.cursor(appointmentResponsePage);
    }


    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> update(@Valid @RequestBody AppointmentUpdateRequest appointmentUpdateRequest) {
        Long doctorId = appointmentUpdateRequest.getDoctor().getId();
        Long animalId = appointmentUpdateRequest.getAnimal().getId();
        Doctor doctor = this.doctorService.get(Math.toIntExact(doctorId));
        Animal animal  = this.animalService.get(Math.toIntExact(animalId));

        Appointment updateAppointment = this.modelMapper.forRequest().map(appointmentUpdateRequest, Appointment.class);
        updateAppointment.setDoctor(doctor);
        updateAppointment.setAnimal(animal);

        this.appointmentService.update(updateAppointment);

        AppointmentResponse appointmentResponse = this.modelMapper.forResponse().map(updateAppointment, AppointmentResponse.class);
        appointmentResponse.setDoctor(doctor);
        appointmentResponse.setAnimal(animal);
        return ResultHelper.successData(appointmentResponse);


    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.appointmentService.delete(id);
        return ResultHelper.ok();
    }

    // TODO Appointments are filtered by the date range entered by the user and the doctor.
//    @GetMapping("/doctor/{doctorId}")
//    @ResponseStatus(HttpStatus.OK)
//    public ResultData<List<AppointmentResponse>> getAppointmentsByDoctorAndDateRange(
//            @PathVariable("doctorId") Long doctorId,
//            @RequestParam("start") LocalDate startDate,
//            @RequestParam("end") LocalDate endDate
//    ) {
//        List<Appointment> appointments = appointmentService.appointmentListByDoctorAndDateRange(doctorId, startDate, endDate);
//        List<AppointmentResponse> appointmentResponses = appointments.stream()
//                .map(AppointmentResponse::fromEntity)
//                .collect(Collectors.toList());
//        return ResultHelper.successData(appointmentResponses);
//    }
//
//    @GetMapping("/animal/{animalId}")
//    @ResponseStatus(HttpStatus.OK)
//    public ResultData<List<AppointmentResponse>> getAppointmentsByAnimalAndDateRange(
//            @PathVariable("animalId") Long animalId,
//            @RequestParam("start") LocalDate startDate,
//            @RequestParam("end") LocalDate endDate
//    ) {
//        List<Appointment> appointments = appointmentService.appointmentListByAnimalAndDateRange(animalId, startDate, endDate);
//        List<AppointmentResponse> appointmentResponses = appointments.stream()
//                .map(AppointmentResponse::fromEntity)
//                .collect(Collectors.toList());
//        return ResultHelper.successData(appointmentResponses);
//    }


}

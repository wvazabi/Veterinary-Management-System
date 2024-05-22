package com.eneskaya.veterinarymanagementsystem.business.abstracts;

import com.eneskaya.veterinarymanagementsystem.dto.request.availableDate.AvailableDateSaveRequest;
import com.eneskaya.veterinarymanagementsystem.dto.request.availableDate.AvailableDateUpdateRequest;
import com.eneskaya.veterinarymanagementsystem.entities.AvailableDate;
import com.eneskaya.veterinarymanagementsystem.entities.Customer;
import org.springframework.data.domain.Page;

public interface IAvailableDateService  {

    AvailableDate save(AvailableDateSaveRequest request);
    AvailableDate get(Long id);
    Page<AvailableDate> cursor(int page, int pageSize);
    AvailableDate update(AvailableDateUpdateRequest request);
    boolean delete(Long id);
}

package com.eneskaya.veterinarymanagementsystem.core.config.modelMapper;

import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;

//Create core package for system files
public interface IModelMapperService {
    ModelMapper forRequest();
    ModelMapper forResponse();
}

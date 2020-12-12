package com.callbackcats.memeow.service;

import com.callbackcats.memeow.repository.TemplateRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TemplateService {
    TemplateRepository templateRepository;
    ModelMapper modelMapper;

    public TemplateService(TemplateRepository templateRepository, ModelMapper modelMapper){
        this.templateRepository = templateRepository;
        this.modelMapper = modelMapper;
    }
}

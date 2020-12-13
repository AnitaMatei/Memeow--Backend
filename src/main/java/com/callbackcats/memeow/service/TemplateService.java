package com.callbackcats.memeow.service;

import com.callbackcats.memeow.model.dto.TemplateDTO;
import com.callbackcats.memeow.model.entity.Template;
import com.callbackcats.memeow.repository.TemplateRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TemplateService {
    TemplateRepository templateRepository;
    ModelMapper modelMapper;

    public TemplateService(TemplateRepository templateRepository, ModelMapper modelMapper){
        this.templateRepository = templateRepository;
        this.modelMapper = modelMapper;
        modelMapper.typeMap(Template.class,TemplateDTO.class).addMappings(mapper -> mapper.map(src -> src.getImageByImageId().getImageUrl(),TemplateDTO::setImageUrl));
    }

    public List<TemplateDTO> searchTemplates(Optional<String> contaningStr, Integer level, Integer pageNumber, Integer pageSize){
        Pageable page = PageRequest.of(pageNumber,pageSize);
        List<Template> templates = contaningStr
                .map(str -> templateRepository.findByIgnoreCaseTemplateNameContainingAndMinRequiredLevelEquals(str,level,page))
                .orElse(templateRepository.findByMinRequiredLevelEquals(level,page));

        return templates.stream()
                .map(template -> modelMapper.map(template, TemplateDTO.class))
                .collect(Collectors.toList());
    }
}

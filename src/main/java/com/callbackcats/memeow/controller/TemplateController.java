package com.callbackcats.memeow.controller;

import com.callbackcats.memeow.model.dto.TemplateDTO;
import com.callbackcats.memeow.service.ImageService;
import com.callbackcats.memeow.service.TemplateService;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/templates")
@Slf4j
public class TemplateController {
    ImageService imageService;
    TemplateService templateService;

    public TemplateController(ImageService imageService, TemplateService templateService){
        this.imageService = imageService;
        this.templateService = templateService;
    }


    @GetMapping("/search")
    @ResponseBody
    public String searchTemplates(@RequestParam(name="contains") Optional<String> containingStr, @RequestParam Integer level, @RequestParam Integer page, @RequestParam Integer pageSize){
        List<TemplateDTO> templateDTOS = templateService.searchTemplates(containingStr,level,page,pageSize);
        log.info("Requesting page of templates "+page+" of size "+pageSize+" with minimum level "+level+".");
        return new GsonBuilder().create().toJson(templateDTOS);
    }

}

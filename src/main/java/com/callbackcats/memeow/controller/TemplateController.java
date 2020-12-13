package com.callbackcats.memeow.controller;

import com.callbackcats.memeow.model.CustomUserPrincipal;
import com.callbackcats.memeow.model.dto.TemplateDTO;
import com.callbackcats.memeow.service.ImageService;
import com.callbackcats.memeow.service.TemplateService;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/templates")
@Slf4j
public class TemplateController {
    ImageService imageService;
    TemplateService templateService;

    public TemplateController(ImageService imageService, TemplateService templateService) {
        this.imageService = imageService;
        this.templateService = templateService;
    }


    /*
    Retrieves list of templates available to user (lower or equal than the user's level). If an user is not logged in it defaults to searching level 1.
     */
    @GetMapping("/available")
    @ResponseBody
    public String findTemplatesAvailable(@RequestParam(name = "contains") Optional<String> containingStr, @RequestParam Optional<Integer> minLevel
            , @RequestParam Integer page, @RequestParam Integer pageSize) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<TemplateDTO> templateDTOS;

        //not logged in
        if (authentication.getPrincipal().getClass() == String.class) {
            log.info("Requesting page " + page + " of size " + pageSize + " of available templates as anonymous.");
            templateDTOS = templateService.searchAvailableTemplates(containingStr, 1, 1, page, pageSize);
        } else {
            CustomUserPrincipal customUserPrincipal = (CustomUserPrincipal) authentication.getPrincipal();
            log.info(customUserPrincipal.getUser().getCurrentLevel().toString());
            log.info("Requesting page " + page + " of size " + pageSize + " of available templates as " + customUserPrincipal.getUsername() + ".");
            templateDTOS = templateService.searchAvailableTemplates(containingStr,
                    customUserPrincipal.getUser().getCurrentLevel(),
                    minLevel.map(level->Integer.min(level,customUserPrincipal.getUser().getCurrentLevel())).orElse(1),
                    page, pageSize);
        }
        return new GsonBuilder().create().toJson(templateDTOS);
    }

    /*
    Retrieves list of templates unavailable to user (higher than the user's level). If an user is not logged in it defaults to searching above level 1 templates.
     */
    @GetMapping("/unavailable")
    @ResponseBody
    public String findTemplatesUnavailable(@RequestParam(name = "contains") Optional<String> containingStr
            , @RequestParam Integer page, @RequestParam Integer pageSize) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<TemplateDTO> templateDTOS;

        //not logged in
        if (authentication.getPrincipal().getClass() == String.class) {
            log.info("Requesting page " + page + " of size " + pageSize + " of unavailable templates as anonymous.");
            templateDTOS = templateService.searchUnavailableTemplates(containingStr, 1, page, pageSize);
        } else {

            CustomUserPrincipal customUserPrincipal = (CustomUserPrincipal) authentication.getPrincipal();
            log.info("Requesting page " + page + " of size " + pageSize + " of unavailable templates as " + customUserPrincipal.getUsername() + ".");
            templateDTOS = templateService.searchUnavailableTemplates(containingStr, customUserPrincipal.getUser().getCurrentLevel(), page, pageSize);
        }
        return new GsonBuilder().create().toJson(templateDTOS);
    }

}

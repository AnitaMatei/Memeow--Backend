package com.callbackcats.memeow.controller;

import com.callbackcats.memeow.exception.BadImageException;
import com.callbackcats.memeow.exception.MemeNotFoundException;
import com.callbackcats.memeow.exception.TemplateNotFoundException;
import com.callbackcats.memeow.model.CustomUserPrincipal;
import com.callbackcats.memeow.model.dto.MemeDTO;
import com.callbackcats.memeow.service.MemeService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("api/memes")
public class MemeController {
    MemeService memeService;

    public MemeController(MemeService memeService) {
        this.memeService = memeService;
    }

    @PostMapping("/create")
    @ResponseBody
    public MemeDTO createMeme(@RequestParam MultipartFile file, @RequestParam String templateName) throws TemplateNotFoundException, BadImageException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication.getPrincipal().getClass() == String.class){
            throw new AccessDeniedException("You are not logged in.");
        }
        CustomUserPrincipal customUserPrincipal = (CustomUserPrincipal)authentication.getPrincipal();

        return memeService.createAndUploadMeme(file, templateName, customUserPrincipal.getUser());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public MemeDTO findMeme(@PathVariable String id) throws MemeNotFoundException {
        return memeService.findMemeByMemeBusinessId(id);
    }

}

package com.callbackcats.memeow.controller;

import com.callbackcats.memeow.model.dto.MemeDTO;
import com.callbackcats.memeow.service.MemeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/memes")
public class MemeController {
    MemeService memeService;

    public MemeController(MemeService memeService){
        this.memeService = memeService;
    }

    @PostMapping("/create")
    @ResponseBody
    public MemeDTO createMeme(@RequestParam  MultipartFile file){
        return memeService.createMeme(file);
    }

}

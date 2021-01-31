package com.callbackcats.memeow.controller;

import com.callbackcats.memeow.model.CustomUserPrincipal;
import com.callbackcats.memeow.model.dto.MemeDTO;
import com.callbackcats.memeow.service.MemeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("api/memes")
public class MemeController {
    MemeService memeService;

    public MemeController(MemeService memeService) {
        this.memeService = memeService;
    }

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<MemeDTO> createMeme(@RequestParam MultipartFile file, @RequestParam String templateName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserPrincipal customUserPrincipal = (CustomUserPrincipal) authentication.getPrincipal();
        MemeDTO memeDTO = memeService.createAndUploadMeme(file, templateName, customUserPrincipal.getUser());
        return ResponseEntity.created(URI.create("api/memes/" + memeDTO.getMemeBusinessId())).body(memeDTO);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<MemeDTO> findMeme(@PathVariable String id){
        return new ResponseEntity<MemeDTO>(memeService.findMemeByMemeBusinessId(id), HttpStatus.FOUND);
    }

    @PutMapping("/{id}/like")
    @ResponseBody
    public ResponseEntity<MemeDTO> likeMeme(@PathVariable String id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserPrincipal customUserPrincipal = (CustomUserPrincipal) authentication.getPrincipal();
        return ResponseEntity.ok(memeService.likeMeme(id,customUserPrincipal.getUsername()));
    }

    @GetMapping("/template/{template}")
    @ResponseBody
    public ResponseEntity<List<MemeDTO>> getMemesByTemplate(@PathVariable String template, @RequestParam Integer pageNumber, @RequestParam Integer pageSize){
        return ResponseEntity.ok(memeService.findMemesByTemplate(template,pageNumber,pageSize));
    }

}

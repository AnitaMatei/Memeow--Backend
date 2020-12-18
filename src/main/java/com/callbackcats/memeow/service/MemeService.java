package com.callbackcats.memeow.service;

import com.callbackcats.memeow.exception.BadImageException;
import com.callbackcats.memeow.exception.MemeNotFoundException;
import com.callbackcats.memeow.exception.TemplateNotFoundException;
import com.callbackcats.memeow.model.dto.MemeDTO;
import com.callbackcats.memeow.model.dto.UserDTO;
import com.callbackcats.memeow.model.entity.Meme;
import com.callbackcats.memeow.repository.MemeRepository;
import com.callbackcats.memeow.repository.TemplateRepository;
import com.callbackcats.memeow.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
public class MemeService {
    MemeRepository memeRepository;
    TemplateRepository templateRepository;
    StorageService storageService;
    UserRepository userRepository;
    ModelMapper modelMapper;

    public MemeService(MemeRepository memeRepository, TemplateRepository templateRepository, StorageService storageService, UserRepository userRepository, ModelMapper modelMapper) {
        this.memeRepository = memeRepository;
        this.templateRepository = templateRepository;
        this.storageService = storageService;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    public MemeDTO createAndUploadMeme(MultipartFile file, String templateName, UserDTO user){
        ByteArrayOutputStream outputStream;
        try {
            outputStream = convertToJpeg(file.getInputStream());
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new BadImageException("File is invalid.");
        }

        MemeDTO meme = saveMeme(templateName, user);
        storageService.uploadImage(new ByteArrayInputStream(outputStream.toByteArray()), meme.getMemeBusinessId() + ".jpeg");

        return meme;
    }

    public MemeDTO saveMeme(String templateName, UserDTO user){
        Meme newMeme = new Meme();

        newMeme.setDateTimeUtc(new Timestamp(new Date().getTime()));
        newMeme.setMemeBusinessId(UUID.randomUUID().toString().replace("-", ""));
        newMeme.setMemeUrl(storageService.getSTORAGE_BASE_URL() + storageService.getMEME_STORAGE_CONTAINER_URL() + newMeme.getMemeBusinessId() + ".jpeg");
        newMeme.getTemplates().add(templateRepository.findByTemplateName(templateName).orElseThrow(() -> new TemplateNotFoundException("Template not found.")));
        newMeme.setUser(userRepository.findByProfileUuid(user.getProfileUuid()).orElse(null));

        return modelMapper.map(memeRepository.save(newMeme), MemeDTO.class);
    }

    public MemeDTO findMemeByMemeBusinessId(String id){
        return memeRepository.findByMemeBusinessId(id)
                .map(meme -> modelMapper.map(meme, MemeDTO.class))
                .orElseThrow(() -> new MemeNotFoundException("Meme not found."));
    }

    private ByteArrayOutputStream convertToJpeg(InputStream inputStream){
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(inputStream);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new BadImageException("File is not an image.");
        }
        BufferedImage nonTransparentCopy = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);    //jesus f christ f this
        nonTransparentCopy.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            ImageIO.write(nonTransparentCopy, "jpeg", outputStream);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new BadImageException("File is not an image.");
        }
        return outputStream;
    }

}

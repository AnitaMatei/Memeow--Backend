package com.callbackcats.memeow.service;

import com.callbackcats.memeow.model.dto.MemeDTO;
import com.callbackcats.memeow.model.entity.Meme;
import com.callbackcats.memeow.repository.MemeRepository;
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
    StorageService storageService;
    ModelMapper modelMapper;

    public MemeService(MemeRepository memeRepository, StorageService storageService, ModelMapper modelMapper) {
        this.memeRepository = memeRepository;
        this.storageService = storageService;
        this.modelMapper = modelMapper;
    }


    public MemeDTO createMeme(MultipartFile file) {
        Meme newMeme = new Meme();
        ByteArrayOutputStream outputStream = null;
        try {
            outputStream = convertToJpeg(file.getInputStream());
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
        newMeme.setDateTimeUtc(new Timestamp(new Date().getTime()));
        newMeme.setMemeBusinessId(UUID.randomUUID().toString().replace("-", ""));
        newMeme.setMemeUrl(storageService.getSTORAGE_BASE_URL() + storageService.getMEME_STORAGE_CONTAINER_URL() + newMeme.getMemeBusinessId()+".jpeg");
        storageService.uploadMeme(new ByteArrayInputStream(outputStream.toByteArray()), newMeme.getMemeBusinessId()+".jpeg");
        memeRepository.save(newMeme);

        return memeRepository.findByMemeBusinessId(newMeme.getMemeBusinessId())
                .map(meme -> modelMapper.map(meme,MemeDTO.class)).orElse(null);
    }

    private ByteArrayOutputStream convertToJpeg(InputStream inputStream) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(inputStream);

        BufferedImage nonTransparentCopy = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);    //jesus f christ f this
        nonTransparentCopy.createGraphics().drawImage(bufferedImage,0,0, Color.WHITE,null);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ImageIO.write(nonTransparentCopy, "jpeg", outputStream);
        return outputStream;
    }

}

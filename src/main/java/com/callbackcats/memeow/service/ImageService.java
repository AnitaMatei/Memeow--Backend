package com.callbackcats.memeow.service;

import com.callbackcats.memeow.model.dto.ImageDTO;
import com.callbackcats.memeow.model.entity.Image;
import com.callbackcats.memeow.repository.ImageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    ModelMapper modelMapper;

    public ImageDTO findByImageId(Integer id) {
        Optional<Image> optionalImage = imageRepository.findByImageId(id);
        ImageDTO imageDTO = optionalImage.isPresent() ? modelMapper.map(optionalImage.get(), ImageDTO.class) : null;

        return imageDTO;
    }
}

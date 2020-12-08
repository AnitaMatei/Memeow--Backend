package com.callbackcats.memeow.service;

import com.callbackcats.memeow.model.dto.ImageDTO;
import com.callbackcats.memeow.model.entity.Image;
import com.callbackcats.memeow.repository.ImageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageService {
    ImageRepository imageRepository;
    ModelMapper modelMapper;

    public ImageService(ImageRepository imageRepository, ModelMapper modelMapper) {
        this.imageRepository = imageRepository;
        this.modelMapper = modelMapper;
    }

    public ImageDTO findByImageId(Integer id) {
        Optional<Image> optionalImage = imageRepository.findByImageId(id);

        return optionalImage.map(image -> modelMapper.map(image, ImageDTO.class)).orElse(null);
    }
}

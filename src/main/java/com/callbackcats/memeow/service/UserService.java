package com.callbackcats.memeow.service;

import com.callbackcats.memeow.model.dto.UserDTO;
import com.callbackcats.memeow.model.entity.User;
import com.callbackcats.memeow.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;
    ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserDTO findByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        return optionalUser.map((user) -> modelMapper.map(user, UserDTO.class)).orElse(null);
    }

    public UserDTO findByProfileUuid(String profileUuid){
        Optional<User> optionalUser = userRepository.findByProfileUuid(profileUuid);

        return optionalUser.map((user) -> modelMapper.map(user, UserDTO.class)).orElse(null);
    }
}

package com.callbackcats.memeow.service;

import com.callbackcats.memeow.model.dto.UserDTO;
import com.callbackcats.memeow.model.entity.User;
import com.callbackcats.memeow.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ModelMapper modelMapper;

    public UserDTO findByEmail(String email){
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty()){
            return null;
        }

        return modelMapper.map(optionalUser.get(),UserDTO.class);
    }
}

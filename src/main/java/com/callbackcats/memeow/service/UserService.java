package com.callbackcats.memeow.service;

import com.callbackcats.memeow.exception.MemeNotFoundException;
import com.callbackcats.memeow.exception.ProfileNotFoundException;
import com.callbackcats.memeow.model.ExperienceTable;
import com.callbackcats.memeow.model.dto.LevelDTO;
import com.callbackcats.memeow.model.dto.MemeDTO;
import com.callbackcats.memeow.model.dto.UserDTO;
import com.callbackcats.memeow.model.entity.Meme;
import com.callbackcats.memeow.model.entity.User;
import com.callbackcats.memeow.repository.LevelRepository;
import com.callbackcats.memeow.repository.MemeRepository;
import com.callbackcats.memeow.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;
    MemeRepository memeRepository;
    ModelMapper modelMapper;
    LevelRepository levelRepository;

    public UserService(UserRepository userRepository, MemeRepository memeRepository, ModelMapper modelMapper, LevelRepository levelRepository) {
        this.userRepository = userRepository;
        this.memeRepository = memeRepository;
        this.modelMapper = modelMapper;
        this.levelRepository = levelRepository;
    }

    public UserDTO findByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        return optionalUser.map((user) -> {
            user.getLevel().setLastTimeRequestedUtc(new Timestamp(new Date().getTime()));
            userRepository.save(user);
            UserDTO profile = modelMapper.map(user, UserDTO.class);
            profile.setLevel(modelMapper.map(user.getLevel(), LevelDTO.class));
            return profile;
        }).orElse(null);
    }

    public UserDTO findProfileByProfileUuid(String profileUuid) throws ProfileNotFoundException {
        Optional<User> optionalUser = userRepository.findByProfileUuid(profileUuid);

        return optionalUser.map((user) -> {
            user.getLevel().setLastTimeRequestedUtc(new Timestamp(new Date().getTime()));
            userRepository.save(user);
            UserDTO profile = modelMapper.map(user, UserDTO.class);
            Optional<Meme> lastMeme = memeRepository.findFirstByUserOrderByDateTimeUtcDesc(user);
            if (lastMeme.isEmpty())
                return profile;

            profile.setLastMeme(modelMapper.map(lastMeme.get(), MemeDTO.class));
            profile.setLevel(modelMapper.map(user.getLevel(), LevelDTO.class));

            return profile;
        })
                .orElseThrow(() -> new ProfileNotFoundException("Profile not found."));
    }

    public void addExperience(MemeDTO meme, Integer experience) {
        User user = memeRepository.findByMemeBusinessId(meme.getMemeBusinessId()).orElseThrow(()->new MemeNotFoundException("That meme does not exist.")).getUser();
        Integer currentLevel = user.getLevel().getCurrentLevel();
        Integer currentXp = user.getLevel().getCurrentXp();

        if (currentXp + experience >= ExperienceTable.XP_TABLE.get(currentLevel)){
            user.getLevel().setCurrentLevel(currentLevel + 1);
            user.getLevel().setCurrentXp(experience - ExperienceTable.XP_TABLE.get(currentLevel ) + currentXp);
        }else{
            user.getLevel().setCurrentXp(currentXp+experience);
        }
        System.out.println(user.getLevel().getCurrentXp());
        userRepository.save(user);
    }
}

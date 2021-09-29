package com.softuni.jsonex.services.impl;

import com.softuni.jsonex.dtos.UserSeedDto;
import com.softuni.jsonex.entities.User;
import com.softuni.jsonex.repositories.UserRepository;
import com.softuni.jsonex.services.UserService;
import com.softuni.jsonex.utils.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.Arrays;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
@Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedUsers(UserSeedDto[] userSeedDtos) {
if (this.userRepository.count()!=0){
    return;
}
Arrays.stream(userSeedDtos)
        .forEach(userSeedDto -> {
            if (this.validationUtil.isValid(userSeedDto)){
                User user=this.modelMapper.map(userSeedDto,User.class);
                this.userRepository.saveAndFlush(user);
            }else {
                this.validationUtil.getViolations(userSeedDto)
                        .stream()
                        .map(ConstraintViolation::getMessage)
                        .forEach(System.out::println);
            }
        });
    }

    @Override
    public User getRandomUser() {
        Random random=new Random();
        long randomId=random.nextInt((int) this.userRepository.count())+1;
        return this.userRepository.getOne(randomId);
    }
}

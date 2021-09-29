package com.softuni.jsonex.services;

import com.softuni.jsonex.dtos.UserSeedDto;
import com.softuni.jsonex.entities.User;

public interface UserService {
    void seedUsers(UserSeedDto[]userSeedDtos);

    User getRandomUser();
}

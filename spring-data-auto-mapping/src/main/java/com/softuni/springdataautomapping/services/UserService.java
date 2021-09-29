package com.softuni.springdataautomapping.services;

import com.softuni.springdataautomapping.domains.dtos.UserLoginDto;
import com.softuni.springdataautomapping.domains.dtos.UserRegisterDto;

public interface UserService {
    void registerUser(UserRegisterDto userRegisterDto);
    void loginUser(UserLoginDto userLoginDto);
    void logout();
    void getRegisteredUserOwnedGames();
}

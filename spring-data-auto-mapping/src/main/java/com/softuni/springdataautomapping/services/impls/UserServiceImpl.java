package com.softuni.springdataautomapping.services.impls;

import com.softuni.springdataautomapping.domains.dtos.UserDto;
import com.softuni.springdataautomapping.domains.dtos.UserLoginDto;
import com.softuni.springdataautomapping.domains.dtos.UserRegisterDto;
import com.softuni.springdataautomapping.domains.entities.Game;
import com.softuni.springdataautomapping.domains.entities.Role;
import com.softuni.springdataautomapping.domains.entities.User;
import com.softuni.springdataautomapping.repositories.UserRepository;
import com.softuni.springdataautomapping.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private UserDto userDto;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerUser(UserRegisterDto userRegisterDto) {
        User user = this.modelMapper.map(userRegisterDto, User.class);

        user.setRole(this.userRepository.count() == 0 ? Role.ADMIN : Role.USER);
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public void loginUser(UserLoginDto userLoginDto) {
        User user = this.userRepository.findByEmail(userLoginDto.getEmail());

        if (user == null) {
            System.out.println("Incorrect username / password");

        } else {
            this.userDto = this.modelMapper.map(user, UserDto.class);
            System.out.printf("Successfully logged in %s\n", user.getFullName());
        }
    }

    @Override
    public void logout() {
        if (this.userDto == null) {
            System.out.println("Cannot log out. No user was logged in.");
        } else {
            System.out.printf("User %s successfully logged out\n", userDto.getFullName());
            this.userDto = null;
        }
    }

    @Override
    public void getRegisteredUserOwnedGames() {
        if(userDto==null){
            System.out.println("There is no registered user.");
            return;
        }
        List<Game> games = userRepository.findByEmailAndPassword(userDto.getEmail(),userDto.getPassword()).getGamesSet();
        games.forEach(g-> System.out.println(g.getTitle()));
    }
}

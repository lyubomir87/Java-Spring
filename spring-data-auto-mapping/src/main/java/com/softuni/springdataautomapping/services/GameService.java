package com.softuni.springdataautomapping.services;

import com.softuni.springdataautomapping.domains.dtos.GameAddDto;

public interface GameService {
    void addGame(GameAddDto gameAddDto);
    void deleteGameById(Long id);
    void printAllGamesData();
    void printGameDataByName(String name);
}

package com.softuni.springdataautomapping.services.impls;

import com.softuni.springdataautomapping.domains.dtos.GameAddDto;
import com.softuni.springdataautomapping.domains.dtos.GameDto;
import com.softuni.springdataautomapping.domains.entities.Game;
import com.softuni.springdataautomapping.repositories.GameRepository;
import com.softuni.springdataautomapping.services.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;
    private   GameDto gameDto;

    public GameServiceImpl(GameRepository gameRepository, ModelMapper modelMapper) {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;


    }


    @Override
    public void addGame(GameAddDto gameAddDto) {
        Game game=this.modelMapper.map(gameAddDto,Game.class);
        this.gameRepository.saveAndFlush(game);

    }

    @Override
    public void deleteGameById(Long id) {
        if(gameRepository.existsById(id)) {
            gameDto = modelMapper.map(gameRepository.getById(id),GameDto.class);
            gameRepository.deleteById(id);
            System.out.printf("Deleted %s%n",this.gameDto.getTitle());
        }
        System.out.printf("There is no game with id %d%n",id);
    }

    @Override
    public void printAllGamesData() {
        gameRepository.getAllByTitleNotNull().forEach(g-> System.out.printf("%s %.2f%n",g.getTitle(),g.getPrice()));
    }

    @Override
    public void printGameDataByName(String name) {
        Game game = gameRepository.findByTitle(name);
        System.out.printf("Title: %s%nPrice: %.2f%nDescription: %s%nRelease date: %s%n",
                game.getTitle(),game.getPrice(),game.getDescription(),game.getReleaseDate());
    }
}

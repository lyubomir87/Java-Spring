package com.softuni.springdataautomapping.repositories;

import com.softuni.springdataautomapping.domains.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game,Long> {
    Game getById(Long id);
    List<Game> getAllByTitleNotNull();
    Game findByTitle(String title);
}

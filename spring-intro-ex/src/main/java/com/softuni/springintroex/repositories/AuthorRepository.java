package com.softuni.springintroex.repositories;

import com.softuni.springintroex.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {

    @Query("select a FROM Author AS a order by a.books.size desc ")
    List<Author> findAuthorByCountOfBooks();
    Author getAuthorByFirstNameAndLastName(String firstName, String lastName);

    List<Author>findAllByFirstNameEndsWith(String str);
}

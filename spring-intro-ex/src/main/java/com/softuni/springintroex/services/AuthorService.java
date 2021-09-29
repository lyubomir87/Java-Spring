package com.softuni.springintroex.services;

import com.softuni.springintroex.entities.Author;
import org.springframework.expression.ParseException;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface AuthorService {
    void seedAuthors() throws IOException;

    int getAuthorsCount();

    Author findAuthorById(Long id);

    List<Author> findAllAuthorsByCountOfBooks();
    Set<Author> getBooksBefore1990() throws ParseException, java.text.ParseException;
    Author getAuthorByFirstAndLastName(String firstName, String lastName);

    List<Author>getAllAuthorsWithFirstNameEndingString(String str);

}

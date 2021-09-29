package com.softuni.springintroex.services;

import com.softuni.springintroex.entities.Author;
import com.softuni.springintroex.repositories.AuthorRepository;
import com.softuni.springintroex.repositories.BookRepository;
import com.softuni.springintroex.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.softuni.springintroex.constants.GlobalConstants.AUTHOR_FILE_PATH;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final FileUtil fileUtil;
@Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository, FileUtil fileUtil) {
        this.authorRepository = authorRepository;
    this.bookRepository = bookRepository;
    this.fileUtil = fileUtil;
    }

    @Override
    public void seedAuthors() throws IOException {
        if (this.authorRepository.count()!=0){
            return;
        }
        String [] fileContent=fileUtil.readFileContent(AUTHOR_FILE_PATH);
        Arrays.stream(fileContent)
                .forEach(r->{
                 String[]params=   r.split("\\s+");
                    String firstName=params [0];
                    String lastName=params[1];
                    Author author=new Author(firstName,lastName);
                    this.authorRepository.saveAndFlush(author);
                });
    }

    @Override
    public int getAuthorsCount() {
        return (int) this.authorRepository.count();
    }

    @Override
    public Author findAuthorById(Long id) {
        return this.authorRepository.getOne(id);
    }

    @Override
    public List<Author> findAllAuthorsByCountOfBooks() {
        return this.authorRepository.findAuthorByCountOfBooks();
    }

    @Override
    public Set<Author> getBooksBefore1990() throws ParseException, java.text.ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
        Date releaseDate = formatter.parse("1/1/1990");
        Set<Author> authors = new HashSet<Author>();
        this.bookRepository.getAllByReleaseDateBefore(releaseDate).forEach(book -> {
            authors.add(book.getAuthor());
        });
        return authors;
    }

    @Override
    public Author getAuthorByFirstAndLastName(String firstName, String lastName) {
        return this.authorRepository.getAuthorByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public List<Author> getAllAuthorsWithFirstNameEndingString(String str) {
        return this.authorRepository.findAllByFirstNameEndsWith(str);
    }


}

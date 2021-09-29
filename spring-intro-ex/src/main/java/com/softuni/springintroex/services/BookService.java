package com.softuni.springintroex.services;

import com.softuni.springintroex.entities.Book;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> getAllBooksAfter2000();

    List<Book> getAllBooksWithAgeRestriction(String agerestriction);
    List<Book>getAllBooksWithEditionTypeAndCopiesLessThan(String edition,int copies);
    List<Book>getAllBooksWithPriceBeforeOrAfter(BigDecimal priceBefore,BigDecimal priceAfter);
    List<Book>getAllBooksWithDateNotIn(int localDate);
    List<Book>getAllBooksWithReleaseDateBefore(String date);
    List<Book>getAllBooksWithContainingString(String containingString);
   List<Book> getAllBooksWithAuthorWithLastNameThatStartWith(String lastNameStr);
   int getNumberOfBooksWithTitleLongerThan(int number);
    int getTotalCopiesByAuthor(String fullName);
    List<String> reducedBook(String title);


}

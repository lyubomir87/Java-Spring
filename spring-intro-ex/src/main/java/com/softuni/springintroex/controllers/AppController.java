package com.softuni.springintroex.controllers;

import com.softuni.springintroex.entities.Book;
import com.softuni.springintroex.entities.EditionType;
import com.softuni.springintroex.services.AuthorService;
import com.softuni.springintroex.services.BookService;
import com.softuni.springintroex.services.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.math.BigDecimal;

@Controller
public class AppController implements CommandLineRunner {
    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;
    private final BufferedReader bufferedReader;

    public AppController(CategoryService categoryService, AuthorService authorService, BookService bookService, BufferedReader bufferedReader) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.bufferedReader = bufferedReader;
    }

   @Override
    public void run(String... args) throws Exception {
    //   categoryService.seedCategories();
    //    authorService.seedAuthors();
     //  bookService.seedBooks();

//1.Books Titles by Age Restriction
    //   System.out.println("Enter age restriction:");
     //   this.bookService.getAllBooksWithAgeRestriction(bufferedReader.readLine())
          //     .forEach(b->{
               //    System.out.printf("%s\n",b.getTitle());
              //  });

       //2.Golden Books
     //  this.bookService.getAllBooksWithEditionTypeAndCopiesLessThan("gold",5000)
            //   .forEach(b->{
               //   System.out.printf("%s\n",b.getTitle());
             // });


       //3.Books by Price
//this.bookService.getAllBooksWithPriceBeforeOrAfter(BigDecimal.valueOf(5),BigDecimal.valueOf(40))
     //   .forEach(b->{
        //    System.out.printf("%s - $%.2f\n",b.getTitle(),b.getPrice());
       // });

       //4.	Not Released Books
      // System.out.println("Enter year:");
    //   this.bookService.getAllBooksWithDateNotIn(Integer.parseInt(bufferedReader.readLine()))
             //  .stream()
              // .map(Book::getTitle)
               //.forEach(System.out::println);

       //5.Books Released Before Date
    //   System.out.println("Enter day month and year:");
     //  this.bookService.getAllBooksWithReleaseDateBefore(bufferedReader.readLine()).
          //     forEach(b->{
             //      System.out.printf("%s %s %.2f\n",b.getTitle(),b.getEditionType(),b.getPrice());
             //  });

       //6.Authors Search
     //  System.out.println("Enter ending string:");
      // this.authorService.getAllAuthorsWithFirstNameEndingString(bufferedReader.readLine())
             //  .forEach(a->{
            //       System.out.printf("%s %s\n",a.getFirstName(),a.getLastName());
            //   });

       //7.Books Search
      // System.out.println("Enter containing String:");
      // this.bookService.getAllBooksWithContainingString(bufferedReader.readLine())
           //    .stream()
           //    .map(Book::getTitle)
             //  .forEach(System.out::println);


       //8.Book Titles Search
      // System.out.println("Enter last name String:");
    //   this.bookService.getAllBooksWithAuthorWithLastNameThatStartWith(this.bufferedReader.readLine())
            //  .forEach(b->{
               //   System.out.printf("%s\n",b.getTitle());
             // });

       //9.Count Books
      // System.out.println("Enter length of title:");
      //System.out.println(bookService.getNumberOfBooksWithTitleLongerThan(Integer.parseInt(this.bufferedReader.readLine())));


       //10.Total Book Copies
      // System.out.println("Enter full name:");
       //System.out.println(this.bookService.getTotalCopiesByAuthor(this.bufferedReader.readLine()));


       //11.Reduced Book
     //  System.out.println("Enter book title:");
      // this.bookService.reducedBook(this.bufferedReader.readLine())
             //  .forEach(b->{
                //   System.out.printf("%s %s %s %.2f",b);
              // });
   }

}

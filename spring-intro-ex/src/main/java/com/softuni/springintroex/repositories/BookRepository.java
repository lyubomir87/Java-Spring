package com.softuni.springintroex.repositories;

import com.softuni.springintroex.entities.AgeRestriction;
import com.softuni.springintroex.entities.Author;
import com.softuni.springintroex.entities.Book;
import com.softuni.springintroex.entities.EditionType;
import com.sun.source.tree.LambdaExpressionTree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book>findAllByReleaseDateAfter(LocalDate localDate);
    List<Book> getAllByReleaseDateBefore(Date date);
    List<Book> getAllByAuthorOrderByReleaseDateDescTitleAsc(Author author);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);
    List<Book>findAllByEditionTypeAndCopiesLessThan(EditionType editionType,int copies);
    List<Book>findAllByPriceLessThanOrPriceGreaterThan(BigDecimal priceBefore,BigDecimal priceAfter);
    List<Book>findAllByReleaseDateBeforeOrReleaseDateAfter(LocalDate localDateBefore,LocalDate localDateAfter);
    List<Book>findAllByReleaseDateBefore(LocalDate localDate);
    List<Book>findAllByTitleContains(String containingString);
    @Query(value = "select b from Book b join Author a on b.author = a where a.lastName like concat(:name,'%') ")
    List<Book>findAllBooksWithAuthorWithLastNameThatStartWith(@Param("name") String lastName);
    @Query("SELECT count(b.title)  FROM Book b WHERE LENGTH(b.title) > :length")
    int findAllBooksWithLengthBiggerThan(@Param("length") int length);



@Query("select sum (b.copies) from Book AS b where concat(b.author.firstName, ' ' ,b.author.lastName)= :name ")
    int findAllCopiesByAuthor(@Param("name") String fullName);

    @Query(value = "select b.title, b.edition_type, b.age_restriction, b.price from books as b where b.title = :title", nativeQuery = true)
    List<Object[]> reducedBook(@Param(value = "title") String title);
}

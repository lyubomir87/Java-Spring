package com.softuni.springintroex.services;

import com.softuni.springintroex.entities.*;

import com.softuni.springintroex.repositories.BookRepository;
import com.softuni.springintroex.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.softuni.springintroex.constants.GlobalConstants.BOOK_FILE_PATH;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final FileUtil fileUtil;
    private final AuthorService authorService;
    private final CategoryService categoryService;


    @Autowired
    public BookServiceImpl(BookRepository bookRepository, FileUtil fileUtil, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.fileUtil = fileUtil;

        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBooks() throws IOException {
        if (this.bookRepository.count() != 0) {
            return;
        }
        String[] fileContent = fileUtil.readFileContent(BOOK_FILE_PATH);
        Arrays.stream(fileContent)
                .forEach(r -> {
                    String[] params = r.split("\\s+");
                    Author author = this.getRandomAuthor();
                    EditionType editionType = EditionType.values()[Integer.parseInt(params[0])];
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
                    LocalDate releaseDate = LocalDate.parse(params[1], formatter);
                    int copies = Integer.parseInt(params[2]);
                    BigDecimal price = new BigDecimal(params[3]);
                    AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(params[4])];
                    String title = this.getTitle(params);
                    Set<Category> categories = this.getRandomCategories();

                    Book book=new Book();
                    book.setAuthor(author);
                    book.setEditionType(editionType);
                    book.setReleaseDate(releaseDate);
                    book.setCopies(copies);
                    book.setPrice(price);
                    book.setAgeRestriction(ageRestriction);
                    book.setTitle(title);
                    book.setCategories(categories);
                    this.bookRepository.saveAndFlush(book);


                });

    }

    @Override
    public List<Book> getAllBooksAfter2000() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate releaseDate = LocalDate.parse("31/12/2000", formatter);
        return this.bookRepository.findAllByReleaseDateAfter(releaseDate);
    }



    @Override
    public List<Book> getAllBooksWithAgeRestriction(String agerestriction) {
        return this.bookRepository.findAllByAgeRestriction(AgeRestriction.valueOf(agerestriction.toUpperCase()));
    }

    @Override
    public List<Book> getAllBooksWithEditionTypeAndCopiesLessThan(String edition, int copies) {
        return
                this.bookRepository.findAllByEditionTypeAndCopiesLessThan(EditionType.valueOf(edition.toUpperCase()),copies);
    }

    @Override
    public List<Book> getAllBooksWithPriceBeforeOrAfter(BigDecimal priceBefore, BigDecimal priceAfter) {
        return this.bookRepository.findAllByPriceLessThanOrPriceGreaterThan(priceBefore,priceAfter);
    }

    @Override
    public List<Book> getAllBooksWithDateNotIn(int year) {
        LocalDate before=LocalDate.of(year,1,1);
        LocalDate after=LocalDate.of(year,12,31);
        return this.bookRepository.findAllByReleaseDateBeforeOrReleaseDateAfter(before,after);
    }

    @Override
    public List<Book> getAllBooksWithReleaseDateBefore(String date) {
        LocalDate localDate=LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return this.bookRepository.findAllByReleaseDateBefore(localDate);
    }

    @Override
    public List<Book> getAllBooksWithContainingString(String containingString) {

        return this.bookRepository.findAllByTitleContains(containingString.toUpperCase());
    }

    @Override
    public List<Book> getAllBooksWithAuthorWithLastNameThatStartWith(String lastNameStr) {
        return this.bookRepository.findAllBooksWithAuthorWithLastNameThatStartWith(lastNameStr);
    }

    @Override
    public int getNumberOfBooksWithTitleLongerThan(int number) {
        return this.bookRepository.findAllBooksWithLengthBiggerThan(number);
    }

    @Override
    public int getTotalCopiesByAuthor(String fullName) {
        return this.bookRepository.findAllCopiesByAuthor(fullName);
    }

    @Override
    public List<String> reducedBook(String title) {
        List<String> result = new ArrayList<String>();
        for (Object[] objects: this.bookRepository.reducedBook(title)) {
            result.add(String.format("%s %s %s %s", objects[0], EditionType.values()[Integer.parseInt(objects[1].toString())], AgeRestriction.values()[Integer.parseInt(objects[2].toString())], objects[3]));
            
        }
        return result;
    }


    private Set<Category> getRandomCategories() {
        Set<Category> result = new HashSet<>();
        Random random = new Random();
        int bound = random.nextInt(3) + 1;

        for (int i = 1; i <= bound; i++) {
            int categoryId=random.nextInt(8)+1;
            result.add(this.categoryService.getCategoryById((long) categoryId));
        }
        return result;
    }

    private String getTitle(String[] params) {
        StringBuilder sb = new StringBuilder();

        for (int i = 5; i < params.length; i++) {
            sb.append(params[i])
                    .append(" ");

        }
        return sb.toString().trim();
    }

    private Author getRandomAuthor() {
        Random random = new Random();
        int randomId = random.nextInt(this.authorService.getAuthorsCount()) + 1;
        return this.authorService.findAuthorById((long) randomId);
    }
}

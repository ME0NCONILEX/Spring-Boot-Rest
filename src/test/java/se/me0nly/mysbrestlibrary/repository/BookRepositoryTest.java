package se.me0nly.mysbrestlibrary.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.me0nly.mysbrestlibrary.entity.Book;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
class BookRepositoryTest {

    Book testBook;
    Book testSecondBook;

    BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @BeforeEach
    public void setUp() {
        testBook = new Book();
        testBook.setTitle("Book's title");
        testBook.setAvailable(true);
        testBook.setReserved(false);
        testBook.setMaxLoanDays(90);
        testBook.setFinePerDay(BigDecimal.ONE);
        testBook.setDescription("Description's test");

        bookRepository.save(testBook);

        testSecondBook = new Book();
        testSecondBook.setTitle("Book's title second test");
        testSecondBook.setAvailable(false);
        testSecondBook.setReserved(true);
        testSecondBook.setMaxLoanDays(90);
        testSecondBook.setFinePerDay(BigDecimal.ONE);
        testSecondBook.setDescription("Description's second test");

        bookRepository.save(testSecondBook);

    }

    @Test
    public void test_findById (){
        List<Book> bookList = new ArrayList<>();
        bookRepository.findAll().iterator().forEachRemaining(bookList::add);
        Integer expectedId = bookList.get(0).getBookId();
        Optional<Book> actualId= bookRepository.findById(expectedId);

        assertEquals("Book's title", actualId.get().getTitle());
    }

    @Test
    public void test_findAll(){
        List<Book> bookList = new ArrayList<>();
        bookRepository.findAll().iterator().forEachRemaining(bookList::add);

        assertEquals(2, bookList.size());
    }

    @Test
    public void test_delete(){
        List<Book> bookList = new ArrayList<>();
        bookRepository.delete(testBook);
        bookRepository.delete(testSecondBook);
        List<Book> emptyList= new ArrayList<>();
        bookRepository.findAll().iterator().forEachRemaining(bookList::add);
        assertEquals(emptyList,new ArrayList<>());

    }

    @Test
    void test_findBooksByAvailable() {
        assertEquals(90, bookRepository.findByAvailable(true).get(0).getMaxLoanDays());
    }

    @Test
    void test_findBooksByReserved() {
        assertEquals(90, bookRepository.findByReserved(false).get(0).getMaxLoanDays());
    }

    @Test
    void test_findBookByTitle() {
        assertEquals(90, bookRepository.findBookByTitle("Book's title second test").get(0).getMaxLoanDays());
    }
}
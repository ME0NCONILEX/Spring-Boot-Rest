package se.me0nly.mysbrestlibrary.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.me0nly.mysbrestlibrary.dto.BookDto;
import se.me0nly.mysbrestlibrary.entity.Book;
import se.me0nly.mysbrestlibrary.exception.DataNotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BookServiceImplTest {

    BookService testObject;
    BookDto bookDto;
    BookDto secondbookDto;

    @Autowired
    public void setTestObject(BookService testObject) {
        this.testObject = testObject;
    }

    @BeforeEach
    void setUp() {
        bookDto = new BookDto();
        bookDto.setTitle("Book's title test");
        bookDto.setAvailable(true);
        bookDto.setReserved(false);
        bookDto.setFinePerDay(BigDecimal.TEN);
        bookDto.setMaxLoanDays(90);
        bookDto.setDescription("Description's test");

        testObject.create(bookDto);

        secondbookDto = new BookDto();
        secondbookDto.setTitle("Book's title second test");
        secondbookDto.setAvailable(false);
        secondbookDto.setReserved(true);
        secondbookDto.setFinePerDay(BigDecimal.TEN);
        secondbookDto.setMaxLoanDays(90);
        secondbookDto.setDescription("Description's second test");

        testObject.create(secondbookDto);
    }


    @Test
    void findByReserved() {
        assertEquals(bookDto.getTitle(), testObject.findByReserved(false).get(0).getTitle());
    }

    @Test
    void findByAvailable() {
        assertEquals(bookDto.getTitle(), testObject.findByAvailable(true).get(0).getTitle());

    }

    @Test
    void findByTitle() {
        assertEquals(bookDto.getDescription(), testObject.findByTitle("Book's title test").get(0).getDescription());
    }
    // findByTitle's test it's working - try it separate!
    @Test
    public void findById()  {
        try {
            assertEquals(bookDto.getTitle(), testObject.findById(testObject.findAll().get(0).getBookId()).getTitle());
        } catch (DataNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void findAll() {
        assertEquals(2, testObject.findAll().size());
    }
    // findAll's test it's working - try it separate!
    @Test
    void create() {
        assertEquals("Book's title test", testObject.create(bookDto).getTitle());
    }
    // create's test it's working - try it separate!
    @Test
    void update() throws DataNotFoundException {
        bookDto.setBookId(1);
        bookDto.setDescription("Updated description");
        assertEquals("Updated description", testObject.update(bookDto).getDescription());
    }

    @Test
    void delete() throws DataNotFoundException {
       assertEquals(2, testObject.findAll().size());
        testObject.delete(1);
        assertEquals(1, testObject.findAll().size());
    }
    // delete's test it's working - try it separate!
}
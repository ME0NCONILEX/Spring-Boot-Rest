package se.me0nly.mysbrestlibrary.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.me0nly.mysbrestlibrary.dto.BookDto;
import se.me0nly.mysbrestlibrary.dto.LibraryUserDto;
import se.me0nly.mysbrestlibrary.dto.LoanDto;
import se.me0nly.mysbrestlibrary.exception.DataNotFoundException;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
class LoanServiceImplTest {


    LoanDto loanDto;
    LoanDto secondloanDto;
    LibraryUserDto loanTaker;
    BookDto bookDto;

    LoanService testObject;
    BookService testBookService;
    LibraryUserService testLibraryUserService;


    @Autowired
    public void setTestObject(LoanService testObject) {
        this.testObject = testObject;
    }

    @Autowired
    public void setTestBookService(BookService testBookService) {
        this.testBookService = testBookService;
    }

    @Autowired
    public void setTestLibraryUserService(LibraryUserService testLibraryUserService) {
        this.testLibraryUserService = testLibraryUserService;
    }

    @BeforeEach
    void setUp() {

        bookDto = new BookDto();
        bookDto.setDescription("Description's test");
        bookDto.setMaxLoanDays(90);
        bookDto.setReserved(false);
        bookDto.setAvailable(true);
        bookDto.setTitle("Book's title test");
        bookDto.setFinePerDay(BigDecimal.TEN);

        testBookService.create(bookDto);

        loanTaker = new LibraryUserDto();
        loanTaker.setName("Library user name's test");
        loanTaker.setEmail("firsttest@n0n0mail.se");
        loanTaker.setRegDate(LocalDate.now());

        testLibraryUserService.create(loanTaker);


        loanDto = new LoanDto();
        loanDto.setLoanDate(LocalDate.now());
        loanDto.setLoanTaker(loanTaker);
        loanDto.setBook(bookDto);
        loanDto.setTerminated(true);

        testObject.create(secondloanDto);
    }
}
// ??? The most in the "test" is ok but e few tests have to be reviewed.
/*
    @Test
    public void findById() throws DataNotFoundException {
        assertEquals(1, testObject.findById(1).getLoanId());
    }

    @Test
    void findByBookId() {
    }

    @Test
    void findByUserId() {
    }

    @Test
    void findByTerminated() {
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
        assertEquals(loanTaker, testObject.create(loanDto).getLoanTaker());
    }
    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}

 */
package se.me0nly.mysbrestlibrary.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LoanDtoTest {

    LoanDto testObject;
    BookDto bookDto;
    LibraryUserDto libraryUserDto;

    LoanDto actualTestObject;
    BookDto actualBookDto;
    LibraryUserDto actualLibraryUserDto;


    @BeforeEach
    void setUp() {
        bookDto = new BookDto();
        bookDto.setTitle("Book's title test");
        bookDto.setAvailable(true);
        bookDto.setReserved(false);
        bookDto.setFinePerDay(BigDecimal.TEN);
        bookDto.setMaxLoanDays(90);
        bookDto.setDescription("Description's test");

        testObject = new LoanDto();
        testObject.setBook(bookDto);
        testObject.setLoanTaker(libraryUserDto);
        testObject.setLoanDate(LocalDate.now());
        testObject.setTerminated(false);
    }

    @Test
    public void create (){
        assertEquals(90, testObject.getBook().getMaxLoanDays());
        assertEquals(libraryUserDto, testObject.getLoanTaker());

    }

    @Test
    void testHashCode() {

        actualBookDto = new BookDto();
        actualBookDto.setTitle("Book's title test");
        actualBookDto.setAvailable(true);
        actualBookDto.setReserved(false);
        actualBookDto.setFinePerDay(BigDecimal.TEN);
        actualBookDto.setMaxLoanDays(90);
        actualBookDto.setDescription("Description's test");

        actualTestObject = new LoanDto();
        actualTestObject.setBook(actualBookDto);
        actualTestObject.setLoanTaker(actualLibraryUserDto);
        actualTestObject.setLoanDate(LocalDate.now());
        actualTestObject.setTerminated(false);

        assertEquals(actualTestObject.hashCode(),testObject.hashCode());


    }
}
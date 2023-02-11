package se.me0nly.mysbrestlibrary.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class LoanTest {

    Loan testObject;
    Book testBook;
    LibraryUser testUser;

    @BeforeEach
    public void setup(){
        testObject = new Loan();
        testObject.setLoanTaker(testUser);
        testObject.setBook(testBook);
        testObject.setLoanDate(LocalDate.now());
        testObject.setTerminated(true);
    }

    @Test
    public void test_create(){
        Assertions.assertEquals(testUser, testObject.getLoanTaker());
        Assertions.assertTrue(testObject.isTerminated());
    }

    @Test
    public void test_hashCode(){
        Loan expectedResult = new Loan();
        expectedResult.setLoanTaker(testUser);
        expectedResult.setBook(testBook);
        expectedResult.setLoanDate(LocalDate.now());
        expectedResult.setTerminated(true);

        Assertions.assertEquals(expectedResult.hashCode(), testObject.hashCode());

    }
}

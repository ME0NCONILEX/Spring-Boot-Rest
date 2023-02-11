package se.me0nly.mysbrestlibrary.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class BookTest {

    Book testObject;

    @BeforeEach
    public void setup() {
        testObject = new Book();
        testObject.setTitle("Book's title test");
        testObject.setAvailable(true);
        testObject.setReserved(false);
        testObject.setMaxLoanDays(90);
        testObject.setFinePerDay(BigDecimal.ONE);
        testObject.setDescription("Description's test");
    }

    @Test
    public void test_create() {
        Assertions.assertEquals("Book's title test", testObject.getTitle());
        Assertions.assertTrue(testObject.isAvailable());
        Assertions.assertEquals(90, testObject.getMaxLoanDays());

    }

    @Test
    public void test_hashCode() {
        Book expectedResult = new Book();
        expectedResult.setTitle("Book's title test");
        expectedResult.setAvailable(true);
        expectedResult.setReserved(false);
        expectedResult.setMaxLoanDays(90);
        expectedResult.setFinePerDay(BigDecimal.ONE);
        expectedResult.setDescription("Description's test");

        Assertions.assertEquals(expectedResult.hashCode(), testObject.hashCode());

    }

}

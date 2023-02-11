package se.me0nly.mysbrestlibrary.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class LibraryUserTest {

    LibraryUser testObject;

    @BeforeEach
    public void setup(){
        testObject = new LibraryUser();
        testObject.setName("User's test");
        testObject.setRegDate(LocalDate.now());
        testObject.setEmail("first@n0mail.se");
    }

    @Test
    public void test_create(){
        Assertions.assertEquals("User's test", testObject.getName());
        Assertions.assertEquals(LocalDate.now(), testObject.getRegDate());
    }

    @Test
    public void test_hashCode(){
        LibraryUser expectedResult= new LibraryUser();
        expectedResult.setName("User's test");
        expectedResult.setRegDate(LocalDate.now());
        expectedResult.setEmail("first@n0mail.se");

        Assertions.assertEquals(expectedResult.hashCode(), testObject.hashCode());

    }
}

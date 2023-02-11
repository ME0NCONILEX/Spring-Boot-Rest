package se.me0nly.mysbrestlibrary.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LibraryUserDtoTest {

    LibraryUserDto testObject;


    @BeforeEach
    void setUp() {
        testObject = new LibraryUserDto();
        testObject.setName("Name's test");
        testObject.setEmail("first@n0mail.se");
        testObject.setRegDate(LocalDate.now());
    }

    @Test
    public void create (){
        assertEquals("Name's test",testObject.getName());
    }

    @Test
    public void testHashCode() {
        LibraryUserDto actual = new LibraryUserDto();
        actual.setName("Name's test");
        actual.setEmail("first@n0mail.se");
        actual.setRegDate(LocalDate.now());
        assertEquals(actual.hashCode(),testObject.hashCode());
    }
}
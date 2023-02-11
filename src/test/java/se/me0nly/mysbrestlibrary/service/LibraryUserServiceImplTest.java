package se.me0nly.mysbrestlibrary.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.me0nly.mysbrestlibrary.dto.LibraryUserDto;
import se.me0nly.mysbrestlibrary.exception.DataNotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LibraryUserServiceImplTest {

    LibraryUserService testObject;
    LibraryUserDto libraryUserDto;
    LibraryUserDto librarysecondUserDto;
    LibraryUserDto librarythirdUserDto;
    LibraryUserDto libraryfourthUserDto;


    @Autowired
    public void setTestObject(LibraryUserService testObject) {
        this.testObject = testObject;
    }

    @BeforeEach
    void setUp() {
        libraryUserDto = new LibraryUserDto();
        libraryUserDto.setEmail("firstUser@n0mail.se");
        libraryUserDto.setRegDate(LocalDate.now());
        libraryUserDto.setName("User name's test");

        librarysecondUserDto = new LibraryUserDto();
        librarysecondUserDto.setEmail("second@n0mail.se");
        librarysecondUserDto.setRegDate(LocalDate.now());
        librarysecondUserDto.setName("Name's second test");

        librarythirdUserDto = new LibraryUserDto();
        librarythirdUserDto.setEmail("magnus@n0mail.se");
        librarythirdUserDto.setRegDate(LocalDate.now());
        librarythirdUserDto.setName("magnus");

        libraryfourthUserDto = new LibraryUserDto();
        libraryfourthUserDto.setEmail("johan@n0mail.se");
        libraryfourthUserDto.setRegDate(LocalDate.now());
        libraryfourthUserDto.setName("johan");

        testObject.create(libraryUserDto);
        testObject.create(librarysecondUserDto);
        testObject.create(librarythirdUserDto);
        testObject.create(libraryfourthUserDto);
    }

    // ??? The most in the "test" is ok but e few tests have to be reviewed.

    @Test
    void findById() throws DataNotFoundException {
        assertEquals("johan", testObject.findById(4).getName());

    }
/*
    @Test
    void findByEmail() throws DataNotFoundException {
        assertEquals("johan", testObject.findByEmail("johan@n0mail.se"));


    }

    @Test
    void findAll() {
        assertEquals(2, testObject.findAll().size());
    }

    @Test
    void create() {
//      assertEquals("magnus@n0mail.se", testObject.create(librarythirdUserDto).getEmail());
//        assertEquals("johan@n0mail.se", testObject.create(libraryfourthUserDto).getEmail());
        assertEquals("n0name@n0n0mail.se", testObject.create(libraryUserDto).getEmail());
    }

    @Test
    void update() throws DataNotFoundException {
        libraryUserDto.setUserId(1);
        libraryUserDto.setName("updated name");
        assertEquals("updated name", testObject.update(libraryUserDto).getName());
    }

    @Test
    void delete() {
        testObject.create(librarysecondUserDto);

        assertEquals(2, testObject.findAll().size());
        testObject.delete(2);
        assertEquals(1, testObject.findAll().size());
    }
    */
}
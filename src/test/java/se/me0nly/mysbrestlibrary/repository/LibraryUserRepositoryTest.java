package se.me0nly.mysbrestlibrary.repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.me0nly.mysbrestlibrary.entity.LibraryUser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
class LibraryUserRepositoryTest {
    LibraryUser libraryUser;
    LibraryUser librarysecondUser;
    LibraryUserRepository libraryUserRepository;
    @Autowired
    public void setLibraryUserRepository(LibraryUserRepository libraryUserRepository) {
        this.libraryUserRepository = libraryUserRepository;
    }
    @BeforeEach
    public void setUp() {
        libraryUser = new LibraryUser();
        libraryUser.setRegDate(LocalDate.now());
        libraryUser.setName("First User's test");
        libraryUser.setEmail("first@n0mail.se");
        librarysecondUser = new LibraryUser();
        librarysecondUser.setRegDate(LocalDate.now());
        librarysecondUser.setName("Second User's test");
        librarysecondUser.setEmail("second@n0mail.se");
        libraryUserRepository.save(libraryUser);
        libraryUserRepository.save(librarysecondUser);
    }
    @Test
    public void test_findById(){
        List<LibraryUser> userList = new ArrayList<>();
        libraryUserRepository.findAll().iterator().forEachRemaining(userList::add);
        Integer expectedId = userList.get(1).getUserId();
        Optional<LibraryUser> actualId = libraryUserRepository.findById(expectedId);
        assertEquals("Second User's test", actualId.get().getName());
    }
    @Test
    public void test_findAll(){
        List<LibraryUser> userList = new ArrayList<>();
        libraryUserRepository.findAll().iterator().forEachRemaining(userList::add);
        assertEquals(2, userList.size());
    }
    @Test
    public void test_delete (){
        List<LibraryUser> userList = new ArrayList<>();
        libraryUserRepository.delete(libraryUser);
        libraryUserRepository.delete(librarysecondUser);
        List<LibraryUser> emptyList = new ArrayList<>();
        libraryUserRepository.findAll().iterator().forEachRemaining(userList::add);
        assertEquals(emptyList, userList);
    }
    @Test
    public void test_findByEmail(){
        assertEquals("First User's test", libraryUserRepository.findLibraryUserByEmailIgnoreCase("first@n0mail.se").get(0).getName());
    }
}
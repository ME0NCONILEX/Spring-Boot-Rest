package se.me0nly.mysbrestlibrary.repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.me0nly.mysbrestlibrary.entity.Book;
import se.me0nly.mysbrestlibrary.entity.LibraryUser;
import se.me0nly.mysbrestlibrary.entity.Loan;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class LoanRepositoryTest {
    Book book;
    BookRepository bookRepository;
    LibraryUser user;
    LibraryUserRepository userRepository;
    Loan testObject;
    LoanRepository loanRepository;
    @Autowired
    public void setLoanRepository(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }
    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @Autowired
    public void setUserRepository(LibraryUserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @BeforeEach
    void setUp() {
        user = new LibraryUser();
        user.setName("test user 1");
        user.setEmail("test1@test.se");
        user.setRegDate(LocalDate.now());
        userRepository.save(user);
        book = new Book();
        book.setTitle("Test title");
        book.setAvailable(true);
        book.setReserved(false);
        book.setMaxLoanDays(90);
        book.setFinePerDay(BigDecimal.ONE);
        book.setDescription("Test description");
        bookRepository.save(book);
        testObject = new Loan();
        testObject.setLoanTaker(user);
        testObject.setBook(book);
        testObject.setTerminated(true);
        testObject.setLoanDate(LocalDate.now());
        loanRepository.save(testObject);
    }
    @Test
    public void test_findById() {
        List<Loan> loanList = new ArrayList<>();
        loanRepository.findAll().iterator().forEachRemaining(loanList::add);
        Long expectedId = loanList.get(0).getLoanId();
        Optional<Loan> actualId = loanRepository.findById(expectedId);
        assertEquals(user, actualId.get().getLoanTaker());
    }
    @Test
    public void test_findAll() {
        List<Loan> loanList = new ArrayList<>();
        loanRepository.findAll().iterator().forEachRemaining(loanList::add);
        assertEquals(1, loanList.size());
    }
    @Test
    public void test_delete() {
        List<Loan> loanList = new ArrayList<>();
        loanRepository.delete(testObject);
        List<Loan> emptyList = new ArrayList<>();
        loanRepository.findAll().iterator().forEachRemaining(loanList::add);
        assertEquals(emptyList, loanList);
    }
    @Test
    void findLoansByLoanTakerUserId() {
        List<Loan> loanList = new ArrayList<>();
        loanRepository.findAll().iterator().forEachRemaining(loanList::add);
    //    assertTrue(loanRepository.findLoansByLoanTakerUserId(loanList.get(0).getLoanTaker().getUserId()).get(0).isTerminated());
    // ???
    }
    @Test
    void findLoansByBook_BookId() {
        List<Loan> loanList = new ArrayList<>();
        loanRepository.findAll().iterator().forEachRemaining(loanList::add);
        assertEquals(1, loanRepository.findLoansByBook_BookId(loanList.get(0).getBook().getBookId()).size());
    }
    @Test
    void findLoansByTerminated() {
        assertEquals(1, loanRepository.findLoansByTerminated(true).size());
    }
}
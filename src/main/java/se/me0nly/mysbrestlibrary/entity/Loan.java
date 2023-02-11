package se.me0nly.mysbrestlibrary.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long loanId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "library_user_user_id")
    private LibraryUser loanTaker;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "book_book_id")
    private Book book;

    //@Column (nullable = false)
    private LocalDate loanDate;

    @Column(name = "loan_terminated")
    private boolean terminated;


}

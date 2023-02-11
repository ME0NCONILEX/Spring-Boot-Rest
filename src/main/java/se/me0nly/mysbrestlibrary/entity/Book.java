package se.me0nly.mysbrestlibrary.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;
    //@Column(nullable = false)
    private String title;
    //@Column(nullable = false, columnDefinition = "tinyint(1) default 1")
    private boolean available;
    //@Column(nullable = false, columnDefinition = "tinyint(1) default 1")
    private boolean reserved;
    //@Column(nullable = false)
    private int maxLoanDays;
    //@Column(nullable = false)
    private BigDecimal finePerDay;
    //@Column(nullable = false)
    private String description;

}

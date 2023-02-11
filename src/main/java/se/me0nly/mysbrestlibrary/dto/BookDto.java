package se.me0nly.mysbrestlibrary.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookDto {
    private int bookId;
    private String title;
    private boolean available;
    private boolean reserved;
    private int maxLoanDays;
    private BigDecimal finePerDay;
    private String description;

}

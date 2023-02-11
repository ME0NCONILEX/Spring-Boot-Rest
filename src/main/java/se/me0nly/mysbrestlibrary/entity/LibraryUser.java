package se.me0nly.mysbrestlibrary.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class LibraryUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private LocalDate regDate;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String email;


}

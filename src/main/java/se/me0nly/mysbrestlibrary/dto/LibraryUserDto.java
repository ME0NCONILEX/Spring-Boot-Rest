package se.me0nly.mysbrestlibrary.dto;
import lombok.Data;
import java.time.LocalDate;
import java.util.Optional;

@Data
public class LibraryUserDto {
    private int userId;
    private LocalDate regDate;
    private String name;
    private String email;

    public Iterable<Object> findAll() {
        return null;
    }
}

package se.me0nly.mysbrestlibrary.repository;

import org.springframework.data.repository.CrudRepository;
import se.me0nly.mysbrestlibrary.entity.Book;

import java.util.List;

public interface BookRepository extends CrudRepository <Book, Integer> {

    List<Book> findByAvailable (boolean status);
    List<Book> findByReserved (boolean status);
    List<Book> findBookByTitle (String title);


}

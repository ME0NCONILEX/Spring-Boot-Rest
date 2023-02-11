package se.me0nly.mysbrestlibrary.service;

import se.me0nly.mysbrestlibrary.dto.BookDto;
import se.me0nly.mysbrestlibrary.exception.DataNotFoundException;

import java.util.List;

public interface BookService {
    List<BookDto> findByReserved(boolean reserved);
    List<BookDto> findByAvailable(boolean available);
    List<BookDto> findByTitle(String title);
    BookDto findById(int bookId) throws DataNotFoundException;
    List<BookDto> findAll();
    BookDto create (BookDto bookDto);
    BookDto update (BookDto bookDto) throws DataNotFoundException;

    void delete(int bookId) throws DataNotFoundException;
}

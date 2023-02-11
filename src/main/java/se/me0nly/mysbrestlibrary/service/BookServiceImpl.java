package se.me0nly.mysbrestlibrary.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.me0nly.mysbrestlibrary.dto.BookDto;
import se.me0nly.mysbrestlibrary.entity.Book;
import se.me0nly.mysbrestlibrary.exception.DataNotFoundException;
import se.me0nly.mysbrestlibrary.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    BookRepository bookRepository;
    ModelMapper modelMapper;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public List<BookDto> findByReserved(boolean reserved) {
        return bookRepository.findByReserved(reserved).stream().map(book -> modelMapper.map(book, BookDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<BookDto> findByAvailable(boolean available) {

        return bookRepository.findByAvailable(available).stream().map(book -> modelMapper.map(book, BookDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<BookDto> findByTitle(String title) {
        if (title == null) throw new IllegalArgumentException("Title should not be null");
        return bookRepository.findBookByTitle(title).stream().map(book -> modelMapper.map(book, BookDto.class)).collect(Collectors.toList());
    }

    @Override
    public BookDto findById(int bookId) throws DataNotFoundException {
        if (bookId == 0) throw new IllegalArgumentException("Id should not be null");
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()){
            return modelMapper.map(optionalBook.get(), BookDto.class);
        } else throw new DataNotFoundException("BookDto not found");

    }

    @Override
    public List<BookDto> findAll() {
        List<Book> bookList = new ArrayList<>();
        bookRepository.findAll().iterator().forEachRemaining(bookList::add);
        return bookList
                .stream()
                .map(book -> modelMapper.map(book, BookDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public BookDto create(BookDto bookDto) {
        if (bookDto == null) throw new IllegalArgumentException("BookDto should not be null");
        if (bookDto.getBookId() != 0) throw new IllegalArgumentException("Id should be null");
        Book bookEntity = modelMapper.map(bookDto, Book.class);
        Book savedBookToEntity = bookRepository.save(bookEntity);
        return modelMapper.map(savedBookToEntity, BookDto.class);
    }

    @Transactional
    @Override
    public BookDto update(BookDto bookDto) throws DataNotFoundException {
        if (bookDto == null) throw new IllegalArgumentException("BookDto should not be null");
        if (bookDto.getBookId() == 0) throw new IllegalArgumentException("Id should not be null");
        Optional<Book> optionalBook = bookRepository.findById(bookDto.getBookId());
        if (optionalBook.isPresent()) {
            Book bookEntity = modelMapper.map(bookDto, Book.class);
            Book savedBookToEntity = bookRepository.save(bookEntity);
            return modelMapper.map(savedBookToEntity, BookDto.class);

        } else throw new DataNotFoundException("BookDto not found");

    }

    @Override
    public void delete(int bookId) throws DataNotFoundException{
        bookRepository.delete(modelMapper.map(bookRepository.findById(bookId)
                .orElseThrow(()->new DataNotFoundException("Id ")),Book.class));
    }
}

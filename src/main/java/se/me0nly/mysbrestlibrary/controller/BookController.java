package se.me0nly.mysbrestlibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import se.me0nly.mysbrestlibrary.dto.BookDto;
import se.me0nly.mysbrestlibrary.exception.DataNotFoundException;
import se.me0nly.mysbrestlibrary.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {

    BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping ("/{id}")
    public ResponseEntity<BookDto> findById (@PathVariable("id") Integer bookId) throws DataNotFoundException {
        if( bookId == 0 ) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.ok(bookService.findById(bookId));

    }
    @Transactional
    @PostMapping ("/")
    public ResponseEntity<BookDto> create (@RequestBody BookDto dto){
        if(dto == null) {
            if(dto.getBookId()!=0)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.create(dto));

    }
    @Transactional
    @PutMapping("/")
    public ResponseEntity<BookDto> update (@RequestBody BookDto dto) throws DataNotFoundException {
        if (dto.getBookId()== 0) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return ResponseEntity.status(HttpStatus.OK).body(bookService.update(dto));
    }


    @GetMapping ("/search")
    public ResponseEntity<List<BookDto>> search (@RequestParam (value = "title", required = false) String title,
                                                 @RequestParam (value = "available", required = false) boolean available,
                                                 @RequestParam (value = "reserved", required = false) boolean reserved){


        if (title != null){
            return ResponseEntity.ok(bookService.findByTitle(title));
        }
        if (available) {
            return ResponseEntity.ok(bookService.findByAvailable(available));
        }

        if (reserved) {
            return ResponseEntity.ok(bookService.findByReserved(reserved));
        }

        return ResponseEntity.ok(bookService.findAll());
    }






}

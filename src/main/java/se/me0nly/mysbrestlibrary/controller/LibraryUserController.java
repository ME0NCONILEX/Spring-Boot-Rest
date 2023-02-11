package se.me0nly.mysbrestlibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import se.me0nly.mysbrestlibrary.dto.LibraryUserDto;
import se.me0nly.mysbrestlibrary.exception.DataNotFoundException;
import se.me0nly.mysbrestlibrary.service.LibraryUserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/libraryUser")
public class LibraryUserController {

    LibraryUserService libraryUserService;

    @Autowired
    public void setLibraryUserService(LibraryUserService libraryUserService) {
        this.libraryUserService = libraryUserService;
    }

    @GetMapping("/")
    public ResponseEntity<List<LibraryUserDto>> findAll() {
        if (libraryUserService.findAll().isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        else return ResponseEntity.ok(libraryUserService.findAll());

    }

    @GetMapping("/{userId}")
    public ResponseEntity<LibraryUserDto> findById(@PathVariable("userId") Integer userId) throws DataNotFoundException {
        if (userId == 0) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.ok(libraryUserService.findById(userId));

    }

    @GetMapping("/{email}")
    public ResponseEntity<LibraryUserDto> findByEmail(@PathVariable("email") String email) {
        if (email == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.ok(libraryUserService.findByEmail(email));

    }

    @Transactional
    @PostMapping("/")
    public ResponseEntity<LibraryUserDto> create(@RequestBody LibraryUserDto dto) {
        if (dto == null) {
            if (dto.getUserId() != 0) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(libraryUserService.create(dto));

    }

    @PutMapping("/")
    public ResponseEntity<LibraryUserDto> update (@RequestBody LibraryUserDto dto) throws DataNotFoundException {
        if(dto.getUserId() == 0) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return ResponseEntity.status(HttpStatus.OK).body(libraryUserService.update(dto));

    }





}

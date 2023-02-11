package se.me0nly.mysbrestlibrary.service;

import se.me0nly.mysbrestlibrary.dto.LibraryUserDto;
import se.me0nly.mysbrestlibrary.exception.DataNotFoundException;

import java.util.List;

public interface LibraryUserService {
    LibraryUserDto findById(int userId) throws DataNotFoundException;

    LibraryUserDto findByEmail(String email);

    List<LibraryUserDto> findAll();

    LibraryUserDto create(LibraryUserDto libraryUserDto);

    LibraryUserDto update(LibraryUserDto libraryUserDto) throws DataNotFoundException;

    boolean delete(int userId);

}


package se.me0nly.mysbrestlibrary.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.me0nly.mysbrestlibrary.dto.LibraryUserDto;
import se.me0nly.mysbrestlibrary.entity.LibraryUser;
import se.me0nly.mysbrestlibrary.exception.DataNotFoundException;
import se.me0nly.mysbrestlibrary.repository.LibraryUserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibraryUserServiceImpl implements LibraryUserService {

    LibraryUserRepository libraryUserRepository;
    ModelMapper modelMapper;

    @Autowired
    public void setLibraryUserRepository(LibraryUserRepository libraryUserRepository) {
        this.libraryUserRepository = libraryUserRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public LibraryUserDto findById(int userId) throws DataNotFoundException {
        if (userId == 0) throw new IllegalArgumentException("Id should not ge null");
        return modelMapper.map(libraryUserRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("LibraryUserDto not found")), LibraryUserDto.class);
    }

    @Override
    public LibraryUserDto findByEmail(String email) {
        if (email == null) throw new IllegalArgumentException("Email should not be null");
        return modelMapper.map(libraryUserRepository.findLibraryUserByEmailIgnoreCase(email), LibraryUserDto.class);
    }

    @Override
    public List<LibraryUserDto> findAll() {
        List<LibraryUser> libraryUserList = new ArrayList<>();
        libraryUserRepository.findAll().iterator().forEachRemaining(libraryUserList::add);
        return libraryUserList.stream().map(libraryUser -> modelMapper.map(libraryUser, LibraryUserDto.class)).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public LibraryUserDto create(LibraryUserDto libraryUserDto) {
        if (libraryUserDto == null) throw new IllegalArgumentException("LibraryUserDto should not be null");
        if (libraryUserDto.getUserId() != 0) throw new IllegalArgumentException("Id should be null");
        LibraryUser libraryUserEntity = modelMapper.map(libraryUserDto, LibraryUser.class);
        LibraryUser savedToEntity = libraryUserRepository.save(libraryUserEntity);
        return modelMapper.map(savedToEntity, LibraryUserDto.class);
    }

    @Transactional
    @Override
    public LibraryUserDto update(LibraryUserDto libraryUserDto) throws DataNotFoundException {
        if (libraryUserDto == null) throw new IllegalArgumentException("LibraryUserDto should not be null");
        if (libraryUserDto.getUserId() == 0) throw new IllegalArgumentException("Id should not be null");
        Optional<LibraryUser> optionalLibraryUser = libraryUserRepository.findById(libraryUserDto.getUserId());
        if (optionalLibraryUser.isPresent()) {
            LibraryUser libraryUserEntity = modelMapper.map(libraryUserDto, LibraryUser.class);
            LibraryUser savedToEntity = libraryUserRepository.save(libraryUserEntity);
            return modelMapper.map(savedToEntity, LibraryUserDto.class);
        } else throw new DataNotFoundException("LibraryUserDto not found");
    }

    @Override
    public boolean delete(int userId) {
        if (userId == 0) throw new IllegalArgumentException("Id should not be null");
        Optional<LibraryUser> optionalLibraryUser = libraryUserRepository.findById(userId);
        if (optionalLibraryUser.isPresent()) {
            LibraryUser libraryUserEntity = modelMapper.map(optionalLibraryUser, LibraryUser.class);
            libraryUserRepository.delete(libraryUserEntity);
            return true;
        } else throw new IllegalArgumentException("LibraryUser not found");

    }
}

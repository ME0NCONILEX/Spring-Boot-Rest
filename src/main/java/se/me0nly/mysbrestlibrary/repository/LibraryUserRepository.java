package se.me0nly.mysbrestlibrary.repository;

import org.springframework.data.repository.CrudRepository;
import se.me0nly.mysbrestlibrary.entity.LibraryUser;

import java.util.List;

public interface LibraryUserRepository extends CrudRepository<LibraryUser, Integer> {

    List<LibraryUser> findLibraryUserByEmailIgnoreCase (String email);

}

package com.nulp.libraries.repository.book;

import com.nulp.libraries.entity.book.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByAuthorName(String name);
}

package com.nulp.libraries;

import com.nulp.libraries.entity.book.Author;
import com.nulp.libraries.entity.book.Book;
import com.nulp.libraries.entity.library.Library;
import com.nulp.libraries.repository.book.AuthorRepository;
import com.nulp.libraries.repository.book.BookRepository;
import com.nulp.libraries.repository.book.GenreRepository;
import com.nulp.libraries.repository.library.LibraryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class LibrariesApplicationTests {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private LibraryRepository libraryRepository;

    @Test
    void contextLoads() {
        bookRepository.save(Book.builder()
                .author(Author.builder()
                        .build())
                .build());

    }

}

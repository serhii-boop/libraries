package com.nulp.libraries.service;

import com.nulp.libraries.entity.book.Book;
import com.nulp.libraries.entity.dto.BooksDTO;
import com.nulp.libraries.entity.dto.LibraryBooksDTO;
import com.nulp.libraries.entity.library.LibraryBooks;
import com.nulp.libraries.mapper.BookMapper;
import com.nulp.libraries.repository.book.BookRepository;
import com.nulp.libraries.repository.library.LibraryBooksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public List<BooksDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper)
                .collect(toList());
    }
}

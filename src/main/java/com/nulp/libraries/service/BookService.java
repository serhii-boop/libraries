package com.nulp.libraries.service;

import com.nulp.libraries.entity.dto.BooksDTO;
import com.nulp.libraries.mapper.BookMapper;
import com.nulp.libraries.repository.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Transactional(value = "primaryTransactionManager", readOnly = true)
    public List<BooksDTO> getAllBooks(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(0, 10);
        if (pageNumber != null && pageSize != null) {
            pageRequest = PageRequest.of(pageNumber, pageSize);
        }

        return bookRepository.findAll(pageRequest).stream()
                .map(bookMapper)
                .collect(toList());
    }

    @Transactional(value = "primaryTransactionManager", readOnly = true)
    public List<BooksDTO> getAllByName(String bookName, Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(0, 10);
        if (pageNumber != null && pageSize != null) {
            pageRequest = PageRequest.of(pageNumber, pageSize);
        }
        return bookRepository.findAllByTitle(bookName, pageRequest).stream()
                .map(bookMapper)
                .collect(toList());
    }
}

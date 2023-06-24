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
public class LibraryBookService {

    private final LibraryBooksRepository libraryBooksRepository;
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BooksDTO getBookByTenantIdAndBookId(String tenantId, Long bookId) {

        var libraryBooks = libraryBooksRepository.findAllByTenantId(tenantId);

        return libraryBooks.stream().filter(book -> bookId.equals(book.getBookId()))
                .findFirst()
                .map(book -> bookRepository.findById(bookId)
                        .orElseThrow(() -> new RuntimeException(String.format("Book with bookId %d not found", bookId))))
                .map(bookMapper)
                .orElseThrow(() -> new RuntimeException(String.format("Book with bookId %d not found", bookId)));

    }

    private LibraryBooksDTO mapToLibraryBooksDTO(Book book, LibraryBooks libBook) {
        return LibraryBooksDTO.builder()
                .id(book.getId())
                .tenantId(libBook.getTenantId())
                .bookQuantities(libBook.getBookQuantities())
                .genre(book.getGenre().getName())
                .authorName(book.getAuthor().getAuthorName())
                .publishedYear(book.getPublishedYear())
                .description(book.getDescription())
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .publication(book.getPublication())
                .build();
    }

    public List<LibraryBooksDTO> getAllBooksByTenantId(String tenant) {
        var libraryBooks = libraryBooksRepository.findAllByTenantId(tenant);
        List<Long> ids = libraryBooks.stream().map(LibraryBooks::getBookId).collect(toList());
        var books = bookRepository.findAllByIdIn(ids);

        return libraryBooks.stream()
                .flatMap(libBook -> books.stream()
                        .filter(book -> libBook.getBookId() == book.getId())
                        .map(book -> mapToLibraryBooksDTO(book, libBook)))
                .collect(toList());
    }
}

package com.nulp.libraries.service;

import com.nulp.libraries.entity.book.Author;
import com.nulp.libraries.entity.book.Book;
import com.nulp.libraries.entity.book.Genre;
import com.nulp.libraries.entity.dto.BooksDTO;
import com.nulp.libraries.entity.dto.CreateBookRQ;
import com.nulp.libraries.entity.dto.LibraryBooksDTO;
import com.nulp.libraries.entity.library.LibraryBooks;
import com.nulp.libraries.mapper.BookMapper;
import com.nulp.libraries.repository.book.AuthorRepository;
import com.nulp.libraries.repository.book.BookRepository;
import com.nulp.libraries.repository.book.GenreRepository;
import com.nulp.libraries.repository.library.LibraryBooksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class LibraryBookService {

    private final LibraryBooksRepository libraryBooksRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BooksDTO getBookByBookId(Long bookId) {
        return bookRepository.findById(bookId)
                .map(bookMapper)
                .orElseThrow(() -> new RuntimeException(String.format("Book with id %d not found", bookId)));
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

    public List<Object> getAllBooksByTenantId(String tenant, Integer pageNumber, Integer pageSize, String keyword) {
        Pageable pageRequest = PageRequest.of(pageNumber, pageSize);

        if (tenant == null || "".equals(tenant)) {
            return searchBooks(keyword, pageRequest);
        } else {
            return searchLibraryBooks(tenant, keyword, pageRequest);
        }
    }

    @Transactional(value = "primaryTransactionManager")
    public LibraryBooksDTO populateBookToLibrary(CreateBookRQ createBookRQ) {
        Genre genre = genreRepository.findByName(createBookRQ.getBookRQ().getGenre()).orElse(null);
        Author author = authorRepository.findByAuthorName(createBookRQ.getBookRQ().getAuthorName()).orElse(null);

        if (genre == null) {
            genre = genreRepository.save(Genre.builder().name(createBookRQ.getBookRQ().getGenre()).build());
        }

        if (author == null) {
            author = authorRepository.save(Author.builder().authorName(createBookRQ.getBookRQ().getAuthorName()).build());
        }

        if (createBookRQ.getBookId() == null) {
            return addBookToLibrary(createBookRQ, author, genre);
        } else {
            return updateBookingLibrary(createBookRQ, author, genre);
        }
    }

    private LibraryBooksDTO updateBookingLibrary(CreateBookRQ createBookRQ, Author author, Genre genre) {
        var existingBook = bookRepository.findById(createBookRQ.getBookId())
                .orElseThrow(RuntimeException::new);

        var updatedBook = bookRepository.save(Book.builder()
                .id(createBookRQ.getBookId())
                .title(createBookRQ.getBookRQ().getTitle())
                .isbn(createBookRQ.getBookRQ().getIsbn())
                .genre(genre)
                .author(author)
                .description(createBookRQ.getBookRQ().getDescription())
                .publication(createBookRQ.getBookRQ().getPublication())
                .publishedYear(createBookRQ.getBookRQ().getPublishedYear())
                .build());

        var libBook = libraryBooksRepository.findByBookId(updatedBook.getId()).orElseThrow(RuntimeException::new);
        return mapToLibraryBooksDTO(updatedBook, libBook);
    }

    private LibraryBooksDTO addBookToLibrary(CreateBookRQ createBookRQ, Author author, Genre genre) {

        var createdBook = bookRepository.save(Book.builder()
                .title(createBookRQ.getBookRQ().getTitle())
                .isbn(createBookRQ.getBookRQ().getIsbn())
                .genre(genre)
                .author(author)
                .description(createBookRQ.getBookRQ().getDescription())
                .publication(createBookRQ.getBookRQ().getPublication())
                .publishedYear(createBookRQ.getBookRQ().getPublishedYear())
                .build());

        var libraryBook = libraryBooksRepository.save(LibraryBooks.builder()
                .bookId(createdBook.getId())
                .tenantId(createBookRQ.getTenantId())
                .build());
        return mapToLibraryBooksDTO(createdBook, libraryBook);
    }

    private List<Object> searchBooks(String keyword, Pageable pageRequest) {
        if (keyword == null || "".equals(keyword)) {
            return bookRepository.findAll(pageRequest)
                    .getContent()
                    .stream()
                    .map(bookMapper)
                    .collect(Collectors.toList());
        } else {
            return bookRepository.findAll(keyword, pageRequest)
                    .getContent()
                    .stream()
                    .map(bookMapper)
                    .collect(Collectors.toList());
        }
    }

    private List<Object> searchLibraryBooks(String tenant, String keyword, Pageable pageRequest) {
        var libraryBooks = libraryBooksRepository.findAllByTenantId(tenant);
        List<Long> bookIds = libraryBooks.stream().map(LibraryBooks::getBookId).collect(Collectors.toList());

        List<Book> books;
        if (keyword != null) {
            books = bookRepository.findAllByIdIn(bookIds, keyword, pageRequest);
        } else {
            books = bookRepository.findAllByIdIn(bookIds, pageRequest);
        }

        return libraryBooks.stream()
                .flatMap(libBook -> books.stream()
                        .filter(book -> libBook.getBookId() == book.getId())
                        .map(book -> mapToLibraryBooksDTO(book, libBook)))
                .collect(Collectors.toList());
    }

    @Transactional(value = "secondaryTransactionManager")
    public void deleteBookById(Long bookId) {
        libraryBooksRepository.deleteByBookId(bookId);
    }
}

package com.nulp.libraries.mapper;

import com.nulp.libraries.entity.book.Book;
import com.nulp.libraries.entity.dto.BooksDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class BookMapper implements Function<Book, BooksDTO> {

    @Override
    public BooksDTO apply(Book book) {
        return BooksDTO.builder()
                .id(book.getId())
                .genre(book.getGenre().getName())
                .authorName(book.getAuthor().getAuthorName())
                .publishedYear(book.getPublishedYear())
                .description(book.getDescription())
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .publication(book.getPublication())
                .build();
    }
}

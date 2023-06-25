package com.nulp.libraries.entity.dto;

import com.nulp.libraries.entity.book.Author;
import com.nulp.libraries.entity.book.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookRQ {

    private String title;
    private String isbn;
    private String authorName;
    private String genre;
    private int publishedYear;
    private String description;
    private String publication;
}


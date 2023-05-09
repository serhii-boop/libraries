package com.nulp.libraries.entity.book;


import javax.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@Table(schema = "books", name = "books")
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "isbn")
    private String isbn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @Column(name = "published_year")
    private int publishedYear;

    @Column(name = "description")
    private String description;

    @Column(name = "publication")
    private String publication;
}

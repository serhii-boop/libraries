package com.nulp.libraries.repository.book;

import com.nulp.libraries.entity.book.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}

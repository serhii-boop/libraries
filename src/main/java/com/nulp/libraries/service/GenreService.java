package com.nulp.libraries.service;

import com.nulp.libraries.entity.book.Genre;
import com.nulp.libraries.repository.book.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    @Transactional(value = "primaryTransactionManager", readOnly = true)
    public List<Genre> getAll() {
        return genreRepository.findAll().stream()
                .map(genre -> Genre.builder()
                        .id(genre.getId())
                        .name(genre.getName())
                        .build())
                .collect(Collectors.toList());
    }
}

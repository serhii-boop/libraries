package com.nulp.libraries.controller;

import com.nulp.libraries.entity.book.Genre;
import com.nulp.libraries.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/library/genre")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    public ResponseEntity<List<Genre>> getAllGenre() {
        return ResponseEntity.ok(genreService.getAll());
    }
}

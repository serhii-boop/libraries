package com.nulp.libraries.controller;

import com.nulp.libraries.entity.dto.BooksDTO;
import com.nulp.libraries.entity.dto.LibraryBooksDTO;
import com.nulp.libraries.service.BookService;
import com.nulp.libraries.service.LibraryBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/library")
@RequiredArgsConstructor
public class LibraryController {

    private final BookService bookService;
    private final LibraryBookService libraryBookService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from second endpoint");
    }


    @GetMapping("/books")
    @PreAuthorize("hasAuthority('WORKER')")
    public ResponseEntity<List<LibraryBooksDTO>> getAllBooksFromLibrary(@RequestParam("tenant") String tenantId) {
        return ResponseEntity.ok(libraryBookService.getAllBooksByTenantId(tenantId));
    }

    @GetMapping("/books")
    @PreAuthorize("hasAuthority('WORKER')")
    public ResponseEntity<List<BooksDTO>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }
}

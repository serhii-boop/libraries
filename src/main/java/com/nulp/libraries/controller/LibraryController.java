package com.nulp.libraries.controller;

import com.nulp.libraries.entity.dto.BooksDTO;
import com.nulp.libraries.entity.dto.LibraryBooksDTO;
import com.nulp.libraries.entity.library.Library;
import com.nulp.libraries.service.BookService;
import com.nulp.libraries.service.LibraryBookService;
import com.nulp.libraries.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/library")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class LibraryController {

    private final BookService bookService;
    private final LibraryBookService libraryBookService;
    private final LibraryService libraryService;

    @GetMapping
    //@PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from second endpoint");
    }


    @GetMapping("/books")
    public ResponseEntity<List<Object>> getAllBooksFromLibrary(
            @RequestParam(value = "tenant", required = false) String tenantId,
            @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "keyword", required = false) String keyword) {
        return ResponseEntity.ok(libraryBookService.getAllBooksByTenantId(tenantId, pageNumber, pageSize, keyword));
    }


    @GetMapping("/books/{bookId}")
    public ResponseEntity<BooksDTO> getBookFromLibraryById(@PathVariable("bookId") Long bookId) {
        return ResponseEntity.ok(libraryBookService.getBookByBookId(bookId));
    }

    @GetMapping("/books/all")
    //@PreAuthorize("hasAuthority('WORKER')")
    public ResponseEntity<List<BooksDTO>> getAllBooks(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                      @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        return ResponseEntity.ok(bookService.getAllBooks(pageNumber, pageSize));
    }

    @GetMapping("/books/{bookName}")
    public ResponseEntity<List<BooksDTO>> getAllBooksByName(@PathVariable("bookName") String bookName,
                                                            @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                            @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        return ResponseEntity.ok(bookService.getAllByName(bookName, pageNumber, pageSize));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Library>> getAllLibrary() {
        return ResponseEntity.ok(libraryService.getAll());
    }

}

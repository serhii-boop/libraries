package com.nulp.libraries.controller;

import com.nulp.libraries.entity.library.Visitor;
import com.nulp.libraries.service.VisitorBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/library/visitor")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class VisitorController {

    private final VisitorBookService visitorBookService;

    @PostMapping
    public ResponseEntity<Visitor> addVisitor(@RequestBody Visitor visitor) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(visitorBookService.addVisitor(visitor));
    }
}

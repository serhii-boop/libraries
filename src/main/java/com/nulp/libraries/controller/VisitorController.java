package com.nulp.libraries.controller;

import com.nulp.libraries.entity.library.Visitor;
import com.nulp.libraries.service.VisitorBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/library/visitor")
@RequiredArgsConstructor
public class VisitorController {

    private final VisitorBookService visitorBookService;

    @PostMapping
    public ResponseEntity<Visitor> addVisitor(@RequestBody Visitor visitor) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(visitorBookService.addVisitor(visitor));
    }
}

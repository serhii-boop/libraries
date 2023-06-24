package com.nulp.libraries.controller;

import com.nulp.libraries.entity.library.Visitor;
import com.nulp.libraries.service.VisitorBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(value = "/{visitorId}")
    public ResponseEntity<Visitor> getVisitorById(@PathVariable("visitorId") Long visitorId) {
        return ResponseEntity.ok(visitorBookService.getVisitorById(visitorId));
    }

    @GetMapping
    public ResponseEntity<List<Visitor>> getAllVisitorFromLibrary(@RequestParam("tenant") String tenantId,
                                                                  @RequestParam(value = "pageNumber") Integer pageNumber,
                                                                  @RequestParam(value = "pageSize") Integer pageSize) {
        return ResponseEntity.ok(visitorBookService.getAllVisitorFromLibrary(tenantId, pageNumber, pageSize));
    }

    @DeleteMapping("/{visitorId}")
    public void deleteVisitorFromLibrary(@PathVariable("visitorId") Long visitorId) {
        visitorBookService.deleteVisitorFromLibrary(visitorId);
    }


}

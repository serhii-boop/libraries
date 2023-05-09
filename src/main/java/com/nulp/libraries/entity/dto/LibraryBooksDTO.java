package com.nulp.libraries.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LibraryBooksDTO {

    private String tenantId;
    private long id;
    private String title;
    private String isbn;
    private String authorName;
    private String genre;
    private int publishedYear;
    private String description;
    private String publication;
    private int bookQuantities;
}

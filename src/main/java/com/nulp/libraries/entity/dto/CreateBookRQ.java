package com.nulp.libraries.entity.dto;

import com.nulp.libraries.entity.book.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookRQ {

    private String tenantId;
    private Long bookId;
    private BookRQ bookRQ;
}

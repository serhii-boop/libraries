package com.nulp.libraries.entity.library;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@Table(schema = "libraries", name = "library_book")
@AllArgsConstructor
@NoArgsConstructor
public class LibraryBooks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "tenant_id")
    private String tenantId;

    @Column(name = "book_id")
    private long bookId;

    @Column(name = "book_quantities")
    private int bookQuantities;

}

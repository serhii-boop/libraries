package com.nulp.libraries.entity.library;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@Table(schema = "libraries", name = "visitor_books")
@AllArgsConstructor
@NoArgsConstructor
public class VisitorBooks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "book_id")
    private long bookId;
    @Column(name = "tenant_id")
    private String tenantId;
    @Column(name = "visitor_id")
    private long visitorId;
    @Column(name = "day_of_borrowing")
    private LocalDateTime dayOfBorrowing;
    @Column(name = "returning_day")
    private LocalDateTime returningDay;
    @Column(name = "is_returned")
    private boolean isReturned;
}

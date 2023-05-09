package com.nulp.libraries.repository.book;

import com.nulp.libraries.entity.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByIdIn(List<Long> ids);
}

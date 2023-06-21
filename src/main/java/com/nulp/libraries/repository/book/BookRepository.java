package com.nulp.libraries.repository.book;

import com.nulp.libraries.entity.book.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = {"author", "genre"})
    Page<Book> findAll(Pageable pageable);

    List<Book> findAllByIdIn(List<Long> ids);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = {"author", "genre"})
    @Query("select b from Book b where " +
            "b.title like %?1% " +
            "or b.author.authorName like %?1% " +
            "or b.genre.name like %?1%")
    Page<Book> findAllByTitle(String title, Pageable pageable);
}

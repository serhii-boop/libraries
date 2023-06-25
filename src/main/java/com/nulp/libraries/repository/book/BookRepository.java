package com.nulp.libraries.repository.book;

import com.nulp.libraries.entity.book.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Override
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = {"author", "genre"})
    Optional<Book> findById(Long aLong);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = {"author", "genre"})
    @Query("select b from Book b where " +
            "b.title like %?1% " +
            "or b.author.authorName like %?1% " +
            "or b.genre.name like %?1%")
    Page<Book> findAll(String keyword, Pageable pageable);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = {"author", "genre"})
    Page<Book> findAll(Pageable pageable);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = {"author", "genre"})
    @Query("select b from Book b where " +
            "b.id in :ids and (" +
            "b.title like %:keyword% " +
            "or b.author.authorName like %:keyword% " +
            "or b.genre.name like %:keyword%)")
    List<Book> findAllByIdIn(@Param("ids") List<Long> ids, @Param("keyword") String keyword, Pageable pageable);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = {"author", "genre"})
    List<Book> findAllByIdIn(List<Long> ids, Pageable pageable);



    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = {"author", "genre"})
    @Query("select b from Book b where " +
            "b.title like %?1% " +
            "or b.author.authorName like %?1% " +
            "or b.genre.name like %?1%")
    Page<Book> findAllByTitle(String title, Pageable pageable);
}

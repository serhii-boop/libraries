package com.nulp.libraries.repository.library;

import com.nulp.libraries.entity.library.LibraryBooks;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibraryBooksRepository extends JpaRepository<LibraryBooks, Long> {

    List<LibraryBooks> findAllByTenantId(String tenantId, Pageable pageable);
    List<LibraryBooks> findAllByTenantId(String tenantId);

    Optional<LibraryBooks> findByBookId(Long bookId);
    Optional<LibraryBooks> findByTenantId(String tenantId);

    void deleteByBookId(Long bookId);

}

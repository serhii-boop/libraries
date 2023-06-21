package com.nulp.libraries.repository.library;

import com.nulp.libraries.entity.library.LibraryBooks;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibraryBooksRepository extends JpaRepository<LibraryBooks, Long> {

    List<LibraryBooks> findAllByTenantId(String tenantId, PageRequest pageRequest);
}

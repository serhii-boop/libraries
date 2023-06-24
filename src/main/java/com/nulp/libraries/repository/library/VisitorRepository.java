package com.nulp.libraries.repository.library;

import com.nulp.libraries.entity.library.Visitor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long> {

    Page<Visitor> getAllByTenantId(String tenant, Pageable pageable);
}

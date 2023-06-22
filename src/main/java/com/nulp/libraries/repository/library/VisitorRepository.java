package com.nulp.libraries.repository.library;

import com.nulp.libraries.entity.library.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long> {


}

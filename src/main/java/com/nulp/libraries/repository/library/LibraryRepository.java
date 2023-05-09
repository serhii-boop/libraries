package com.nulp.libraries.repository.library;

import com.nulp.libraries.entity.library.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {


}

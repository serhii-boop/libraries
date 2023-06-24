package com.nulp.libraries.service;

import com.nulp.libraries.entity.library.Library;
import com.nulp.libraries.repository.library.LibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibraryService {

    private final LibraryRepository libraryRepository;

    @Transactional(value = "secondaryTransactionManager", readOnly = true)
    public List<Library> getAll() {
        return libraryRepository.findAll();
    }
}

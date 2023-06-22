package com.nulp.libraries.service;

import com.nulp.libraries.entity.library.Visitor;
import com.nulp.libraries.repository.library.VisitorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VisitorBookService {

    private final VisitorRepository visitorRepository;

    @Transactional(value = "secondaryTransactionManager")
    public Visitor addVisitor(Visitor visitor) {
        return visitorRepository.save(visitor);
    }
}

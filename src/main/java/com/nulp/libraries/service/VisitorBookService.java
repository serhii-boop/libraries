package com.nulp.libraries.service;

import com.nulp.libraries.entity.library.Visitor;
import com.nulp.libraries.repository.library.VisitorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitorBookService {

    private final VisitorRepository visitorRepository;

    @Transactional(value = "secondaryTransactionManager")
    public Visitor addVisitor(Visitor visitor) {
        return visitorRepository.save(visitor);
    }

    public Visitor getVisitorById(Long visitorId) {
        return visitorRepository.findById(visitorId)
                .orElseThrow(() -> new RuntimeException(String.format("Visitor with id %d not found", visitorId)));
    }

    public List<Visitor> getAllVisitorFromLibrary(String tenantId, int pageNumber, int pageSize, String keyword) {
        if (keyword == null || "".equals(keyword)) {
            return visitorRepository.getAllByTenantId(tenantId, PageRequest.of(pageNumber, pageSize)).getContent();
        }
        return visitorRepository.getAllByTenantId(tenantId, keyword, PageRequest.of(pageNumber, pageSize)).getContent();
    }

    public void deleteVisitorFromLibrary(Long visitorId) {
        visitorRepository.deleteById(visitorId);
    }
}

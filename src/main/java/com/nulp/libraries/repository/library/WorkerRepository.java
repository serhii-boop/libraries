package com.nulp.libraries.repository.library;

import com.nulp.libraries.entity.library.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkerRepository extends JpaRepository<Worker, Long> {

    Optional<Worker> findByEmail(String email);
}

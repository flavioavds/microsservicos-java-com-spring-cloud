package com.dev.worker.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.worker.entity.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
	Optional<Worker> findById(Long id);
}

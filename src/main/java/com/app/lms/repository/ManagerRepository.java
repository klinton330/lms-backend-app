package com.app.lms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.lms.entity.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

	Optional<Manager> findByManagerName(String name);
}

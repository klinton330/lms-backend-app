package com.app.lms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.lms.entity.Employee;

public interface EmployeeRespository extends JpaRepository<Employee, Long> {
	Optional<Employee> findByEmail(String email);

	Optional<Employee> findByEmployeeName(String name);
}

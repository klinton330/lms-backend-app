package com.app.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.lms.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}

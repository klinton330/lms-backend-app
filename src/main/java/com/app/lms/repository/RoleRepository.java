package com.app.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.lms.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {

}

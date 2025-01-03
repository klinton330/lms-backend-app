package com.app.lms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.lms.entity.LeaveType;

public interface LeaveTypeRepository extends JpaRepository<LeaveType,Long> {

	public boolean existsByLeaveType(String leaveType);
	public Optional<LeaveType> findByLeaveType(String leaveType);
}

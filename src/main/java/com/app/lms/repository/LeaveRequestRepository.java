package com.app.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.lms.entity.LeaveRequest;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

}

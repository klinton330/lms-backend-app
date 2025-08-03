package com.app.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.lms.entity.LeaveApproval;

public interface LeaveApprovalRepository extends JpaRepository<LeaveApproval,Long> {

}

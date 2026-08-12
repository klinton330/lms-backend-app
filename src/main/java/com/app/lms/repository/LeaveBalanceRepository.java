package com.app.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.app.lms.entity.Employee;
import com.app.lms.entity.LeaveBalance;
import com.app.lms.entity.LeaveType;

import jakarta.transaction.Transactional;

public interface LeaveBalanceRepository extends JpaRepository<LeaveBalance,Long> {
	//public boolean existsByEmployeeAndLeavetype(Long employeeId, Long leaveTypeId);

	public boolean existsByEmployeeAndLeavetype(Employee employee, LeaveType leaveType);
	
	@Modifying
	@Transactional
	@Query("""
	    UPDATE LeaveBalance lb
	    SET lb.availableDays = :days
	    WHERE lb.employee.id = :employeeId
	      AND lb.leavetype.id = :leaveTypeId
	""")
	public Integer updateLeaveBalence(Long employeeId,Long leaveTypeId,Long days);
}

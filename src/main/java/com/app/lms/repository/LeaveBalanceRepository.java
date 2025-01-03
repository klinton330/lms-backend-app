package com.app.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.lms.entity.Employee;
import com.app.lms.entity.LeaveBalance;
import com.app.lms.entity.LeaveType;

public interface LeaveBalanceRepository extends JpaRepository<LeaveBalance,Long> {
	//public boolean existsByEmployeeAndLeavetype(Long employeeId, Long leaveTypeId);

	public boolean existsByEmployeeAndLeavetype(Employee employee, LeaveType leaveType);
}

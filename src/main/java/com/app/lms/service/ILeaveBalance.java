package com.app.lms.service;

import java.util.List;

import com.app.lms.entity.LeaveBalance;
import com.app.lms.payload.LeaveBalanceRequestDTO;

public interface ILeaveBalance {

	public void postLeaveBalance(LeaveBalanceRequestDTO leaveBalanceRequestDTO);
	public List<LeaveBalance>  getLeaveBalence(Long Id);
	//public boolean updateLeaveBalance(Long id,LeaveBalanceRequestDTO leaveBalanceRequestDTO);
	public boolean updateLeaveBalance(Long employeeId,Long leaveId,Long days);
}

package com.app.lms.service;

import com.app.lms.payload.LeaveBalanceRequestDTO;

public interface ILeaveBalance {

	public void postLeaveBalance(LeaveBalanceRequestDTO leaveBalanceRequestDTO);
	public boolean updateLeaveBalance(Long id,LeaveBalanceRequestDTO leaveBalanceRequestDTO);
}

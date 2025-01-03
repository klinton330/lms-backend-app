package com.app.lms.service;

import java.util.List;

import com.app.lms.payload.LeaveTypeRequestDTO;
import com.app.lms.payload.LeaveTypeResponseDTO;

public interface ILeaveTypeService {

	public void postLeaveType(LeaveTypeRequestDTO leaveTypeRequestDTO);
	public List<LeaveTypeResponseDTO> getAllLeaveType();
	public boolean updateLeaveType(LeaveTypeRequestDTO leaveTypeRequestDTO);
	public boolean deleteLeaveType(String leaveType);
}

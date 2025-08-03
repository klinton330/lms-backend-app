package com.app.lms.service;

import com.app.lms.payload.LeaveAppliedDTO;
import com.app.lms.payload.LeaveRequestDTO;

public interface ILeaveRequestService {
	public LeaveAppliedDTO postLeaveRequest(LeaveRequestDTO leaveRequestDTO);

}

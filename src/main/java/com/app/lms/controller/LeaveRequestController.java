package com.app.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.lms.payload.LeaveAppliedDTO;
import com.app.lms.payload.LeaveRequestDTO;
import com.app.lms.service.ILeaveRequestService;

@RestController
@RequestMapping("/api/v1/")
public class LeaveRequestController {

	@Autowired
	private ILeaveRequestService leaveRequestServiceImpl;
	@PostMapping("apply")
	public ResponseEntity<LeaveAppliedDTO>postLeave(@RequestBody LeaveRequestDTO leaveRequestDTO){
		LeaveAppliedDTO leaveAppliedDTO=leaveRequestServiceImpl.postLeaveRequest(leaveRequestDTO);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(leaveAppliedDTO);
	}
}

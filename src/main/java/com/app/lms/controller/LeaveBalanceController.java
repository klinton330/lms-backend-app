package com.app.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.lms.constants.LeaveBalanceConstants;
import com.app.lms.constants.LeaveTypeConstants;
import com.app.lms.payload.LeaveBalanceRequestDTO;
import com.app.lms.payload.ResponseDTO;
import com.app.lms.service.ILeaveBalance;

@RestController
@RequestMapping("/api/v1/leavebalance")
public class LeaveBalanceController {

	@Autowired
	private ILeaveBalance leaveBalance;
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<ResponseDTO> postLeavebalance(@RequestBody LeaveBalanceRequestDTO leaveBalanceRequestDTO){
		leaveBalance.postLeaveBalance(leaveBalanceRequestDTO);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDTO(LeaveBalanceConstants.STATUS_201, LeaveBalanceConstants.STATUS_201_MESSAGE));
	}

	@PutMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<ResponseDTO>updateLeavebalance(@RequestParam Long id,@RequestBody LeaveBalanceRequestDTO leaveBalanceRequestDTO){
		boolean isUpdated=leaveBalance.updateLeaveBalance(id, leaveBalanceRequestDTO);
		if(isUpdated)
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDTO(LeaveBalanceConstants.STATUS_200, LeaveBalanceConstants.STATUS_200_MESSAGE));
		else
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDTO(LeaveTypeConstants.STATUS_500, LeaveTypeConstants.STATUS_500_MESSAGE));
	}
}

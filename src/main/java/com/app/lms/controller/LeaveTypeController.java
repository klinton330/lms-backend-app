package com.app.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.lms.constants.LeaveTypeConstants;
import com.app.lms.payload.LeaveTypeRequestDTO;
import com.app.lms.payload.LeaveTypeResponseDTO;
import com.app.lms.payload.ResponseDTO;
import com.app.lms.service.ILeaveTypeService;

@RestController
@RequestMapping("/api/v1/leavetype")
public class LeaveTypeController {
	@Autowired
	private ILeaveTypeService leaveTypeService;

	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<ResponseDTO> postLeaveType(@RequestBody LeaveTypeRequestDTO leaveTypeRequestDTO) {
		leaveTypeService.postLeaveType(leaveTypeRequestDTO);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDTO(LeaveTypeConstants.STATUS_201, LeaveTypeConstants.STATUS_201_MESSAGE));
	}

	@GetMapping
	public ResponseEntity<List<LeaveTypeResponseDTO>> getAllLeavetype() {
		return ResponseEntity.status(HttpStatus.OK).body(leaveTypeService.getAllLeaveType());
	}

	@PutMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<ResponseDTO> updateLeaveType(@RequestBody LeaveTypeRequestDTO leaveTypeRequestDTO) {
		boolean isUpdated = leaveTypeService.updateLeaveType(leaveTypeRequestDTO);
		if (isUpdated)
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDTO(LeaveTypeConstants.STATUS_200, LeaveTypeConstants.STATUS_200_MESSAGE));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseDTO(LeaveTypeConstants.STATUS_500, LeaveTypeConstants.STATUS_500_MESSAGE));

	}

	@DeleteMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<ResponseDTO> deleteLeaveType(@RequestParam String leaveType) {
		boolean isDeleted = leaveTypeService.deleteLeaveType(leaveType);
		if (isDeleted)
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDTO(LeaveTypeConstants.STATUS_204, LeaveTypeConstants.STATUS_204_MESSAGE));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ResponseDTO(LeaveTypeConstants.STATUS_500, LeaveTypeConstants.STATUS_500_MESSAGE));
	}

}

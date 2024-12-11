package com.app.lms.controller;

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

import com.app.lms.constants.EmployeeConstants;
import com.app.lms.constants.ManagerConstants;
import com.app.lms.payload.EmployeeRequestDTO;
import com.app.lms.payload.EmployeeResponseDTO;
import com.app.lms.payload.ResponseDTO;
import com.app.lms.service.IEmployeeService;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

	@Autowired
	private IEmployeeService employeeService;

	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<ResponseDTO> postEmployee(@RequestBody EmployeeRequestDTO employeeRequestDTO) {
		employeeService.postEmployee(employeeRequestDTO);
		return ResponseEntity.status(HttpStatus.CREATED).
				body(new ResponseDTO(EmployeeConstants.STATUS_201, EmployeeConstants.STATUS_201_MESSAGE));

	}

	@GetMapping
	public ResponseEntity<EmployeeResponseDTO> fetchEmployee(@RequestParam String email) {
		EmployeeResponseDTO employeeResponseDTO = employeeService.fetchEmployee(email);
		return ResponseEntity.status(HttpStatus.OK).body(employeeResponseDTO);

	}

	@PutMapping
	public ResponseEntity<ResponseDTO> updateEmployee(@RequestBody EmployeeRequestDTO employeeRequestDTO) {
		boolean isUpdated = employeeService.UpdateEmployee(employeeRequestDTO);
		if (isUpdated)
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDTO(EmployeeConstants.STATUS_200, EmployeeConstants.STATUS_200_MESSAGE));
		else
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDTO(EmployeeConstants.STATUS_500, EmployeeConstants.STATUS_500_MESSAGE));
	}

	@DeleteMapping
	public ResponseEntity<ResponseDTO> deleteEmployee(@RequestParam String email) {
		boolean isDeleted = employeeService.deleteEmployee(email);
		if (isDeleted)
			return ResponseEntity.status(HttpStatus.NO_CONTENT)
					.body(new ResponseDTO(EmployeeConstants.STATUS_200, EmployeeConstants.STATUS_200_MESSAGE));
		else
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDTO(EmployeeConstants.STATUS_500, EmployeeConstants.STATUS_500_MESSAGE));
	}
}

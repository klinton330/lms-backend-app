package com.app.lms.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.lms.constants.EmployeeConstants;
import com.app.lms.entity.Employee;
import com.app.lms.payload.ResponseDTO;
import com.app.lms.service.EmailService;
import com.app.lms.service.IEmployeeService;
import com.app.lms.service.IPasswordResetService;

@RestController
@RequestMapping("/api/v1/password")
public class PasswordResetServiceController {
	@Autowired
	private IEmployeeService employeeService;

	@Autowired
	private IPasswordResetService passwordResetService;

	@Autowired
	private EmailService emailService;

	@PostMapping("/forgot")
	public ResponseEntity<ResponseDTO> createToken(@RequestParam String email) {
		Employee employee = employeeService.getEmployeeData(email);
		System.out.println("Employe Name:"+employee.getEmail()+" "+employee.getEmployeeName());
		String token = UUID.randomUUID().toString();
	passwordResetService.createPasswordResetTokenForUser(employee, token);
		String resetUrl = "http://localhost:8080/api/v1/password/reset?token=" + token;
		emailService.sendResetTokenURL(resetUrl, "klintonece@gmail.com", employee.getEmployeeName());
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseDTO(EmployeeConstants.STATUS_200, EmployeeConstants.STATUS_RESET_LINK_MESSAGE));
	}
}

package com.app.lms.service;

import org.springframework.stereotype.Service;

import com.app.lms.entity.Employee;

@Service
public interface IPasswordResetService {

	public void createPasswordResetTokenForUser(Employee employee, String token);

	public boolean  validatePasswordResetToken(String token);

	public void changeUserPassword(String token,String newPassword);

}

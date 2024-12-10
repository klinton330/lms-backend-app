package com.app.lms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.lms.entity.Employee;
import com.app.lms.entity.PasswordResetToken;
import com.app.lms.repository.PasswordResetTokenRepository;
import com.app.lms.service.IPasswordResetService;

@Service
public class PasswordResetServiceImpl implements IPasswordResetService {

	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;

	@Override
	public void createPasswordResetTokenForUser(Employee employee, String token) {
		PasswordResetToken passwordResetToken = new PasswordResetToken(token, employee);
		passwordResetTokenRepository.save(passwordResetToken);

	}

	@Override
	public Employee validatePasswordResetToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changeUserPassword(Employee employee, String newPassword) {
		// TODO Auto-generated method stub

	}

}

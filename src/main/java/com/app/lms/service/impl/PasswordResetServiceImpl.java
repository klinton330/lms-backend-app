package com.app.lms.service.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.lms.entity.Employee;
import com.app.lms.entity.PasswordResetToken;
import com.app.lms.exception.TokenNotValidException;
import com.app.lms.repository.EmployeeRespository;
import com.app.lms.repository.PasswordResetTokenRepository;
import com.app.lms.service.IPasswordResetService;

@Service
public class PasswordResetServiceImpl implements IPasswordResetService {
	private static final long EXPIRE_TOKEN = 30;
	@Autowired
	private EmployeeRespository employeeRespository;
	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void createPasswordResetTokenForUser(Employee employee, String token) {
		PasswordResetToken passwordResetToken = new PasswordResetToken(token, employee);
		passwordResetToken.setTokenCreationDate(LocalDateTime.now());
		passwordResetTokenRepository.save(passwordResetToken);

	}

	@Override
	public boolean validatePasswordResetToken(String token) {
		Optional<PasswordResetToken> validateToken = passwordResetTokenRepository.findByToken(token);
		if (!validateToken.isPresent()) {
			return false;
		}
		LocalDateTime tokenCreationDate = validateToken.get().getTokenCreationDate();
		if (isTokenExpired(tokenCreationDate))
			return false;
		else
			return true;
	}

	private boolean isTokenExpired(final LocalDateTime tokenCreationDate) {
		LocalDateTime now = LocalDateTime.now();
		Duration diff = Duration.between(tokenCreationDate, now);
		return diff.toMinutes() >= EXPIRE_TOKEN;
	}

	@Override
	public void changeUserPassword(String token, String newPassword) {
		if (validatePasswordResetToken(token)) {
			System.out.println("token");
			Optional<PasswordResetToken> validateToken = passwordResetTokenRepository.findByToken(token);
			Employee employee = validateToken.get().getEmployee();
			employee.setPassword(passwordEncoder.encode(newPassword));
			employeeRespository.save(employee);
		} else {
			throw new TokenNotValidException("Invalid Token");
		}
	}

}

package com.app.lms.entity;

import java.util.Calendar;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class PasswordResetToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String token;
	@OneToOne(targetEntity = Employee.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "emp_id")
	private Employee employee;
	private Date expiryDate;

	public PasswordResetToken(String token, Employee employee) {
		super();
		this.token = token;
		this.employee = employee;
		this.expiryDate = calculateExpiryDate(24 * 60);
	}

	public PasswordResetToken() {
		super();
	}

	private Date calculateExpiryDate(int expiryTimeInMinutes) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MINUTE, expiryTimeInMinutes);
		return calendar.getTime();
	}

}
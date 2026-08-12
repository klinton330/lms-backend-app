package com.app.lms.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class PasswordResetToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String token;
	@ManyToOne
	@JoinColumn(name = "emp_id", nullable = false)
	@JsonIgnore
	private Employee employee;
	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime tokenCreationDate;

	public PasswordResetToken(String token, Employee employee) {
		super();
		this.token = token;
		this.employee = employee;
		//this.expiryDate = calculateExpiryDate(24 * 60);
	}

	public PasswordResetToken() {
		super();
	}


}

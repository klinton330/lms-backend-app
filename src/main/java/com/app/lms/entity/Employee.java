package com.app.lms.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long employeeId;
	private String employeeName;
	private String email;
	private String mobileNumber;
	private String password;
	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
//	@OnDelete(action = OnDeleteAction.SET_NULL)
	@JsonIgnore
	private Role roles;
	@ManyToOne
	@JoinColumn(name = "manager_id", nullable = false)
//	@OnDelete(action =OnDeleteAction.SET_NULL)
	@JsonIgnore
	private Manager manager;
	@ManyToOne
	@JoinColumn(name = "department_id", nullable = false)
//	@OnDelete(action = OnDeleteAction.SET_NULL)
	@JsonIgnore
	private Department department;

}

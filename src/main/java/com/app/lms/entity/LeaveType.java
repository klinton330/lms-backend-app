package com.app.lms.entity;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class LeaveType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "leave_type", nullable = false)
	private String leaveType;
	@Column(name = "description")
	private String description;
	@Column(name = "max_days")
	private int maxDays;
	@OneToMany(mappedBy = "leaveType",cascade = CascadeType.PERSIST, orphanRemoval = false)
	List<LeaveRequest>leaveRequests;
}

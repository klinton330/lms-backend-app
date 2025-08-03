package com.app.lms.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class LeaveApproval {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
    private ELeaveStatus status;
	
	private String comments;
	
	private LocalDate actionDate;
	
	@OneToOne
    @JoinColumn(name = "leave_request_id")
	LeaveRequest leaveRequest;
	
	@ManyToOne
	@JoinColumn(name = "manager_id", nullable = false)
	Manager manager;

}

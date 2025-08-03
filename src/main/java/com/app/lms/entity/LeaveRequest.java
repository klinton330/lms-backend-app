package com.app.lms.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
@Entity
@Data
public class LeaveRequest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate fromDate;
    private LocalDate toDate;
    private String reason;
    private int totaldays;
    
    @ManyToOne
    @JoinColumn(name = "leavetype_id")
    private LeaveType leaveType;
    
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;
    
    @OneToOne(mappedBy = "leaveRequest", cascade = CascadeType.ALL)
    private LeaveApproval leaveApproval;
}

package com.app.lms.payload;

import java.util.List;

import com.app.lms.entity.Department;
import com.app.lms.entity.Manager;
import com.app.lms.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDTO {
	private Long id;
	private String employeeName;
	private String mobileNumber;
	private String email;
	private Role role;
	private Manager manager;
	private Department department;
	List<EmployeeForLeaveBalance>leavebalance;
}

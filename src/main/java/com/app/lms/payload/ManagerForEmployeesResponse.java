package com.app.lms.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ManagerForEmployeesResponse {
	private Long id;
	private String employeeName;
	private String mobileNumber;
	private String email;
	private String managerName;
	private String department;
}

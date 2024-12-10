package com.app.lms.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequestDTO {
	private String empName;
	private String email;
	private String password;
	private String mobileNumber;
	private Long roleid;
	private Long managerid;
	private Long departmentid;
}

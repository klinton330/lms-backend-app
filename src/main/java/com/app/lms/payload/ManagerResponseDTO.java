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
public class ManagerResponseDTO {
	public Long id;
	public String managerName;
	public String email;
	public List<ManagerForEmployeesResponse>employee;
}

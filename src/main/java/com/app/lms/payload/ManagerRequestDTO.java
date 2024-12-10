package com.app.lms.payload;

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
public class ManagerRequestDTO {
	public String managerName;
	public String email;
}

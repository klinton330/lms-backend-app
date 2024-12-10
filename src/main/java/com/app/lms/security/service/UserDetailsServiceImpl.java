package com.app.lms.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.lms.entity.Employee;
import com.app.lms.exception.ResourceNotFoundException;
import com.app.lms.repository.EmployeeRespository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private EmployeeRespository employeeRespository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Employee employee = employeeRespository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("Email Id not found in DB:" + email));
		return UserDetailsImpl.build(employee);
	}

}

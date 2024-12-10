package com.app.lms.service;

import org.springframework.stereotype.Service;

import com.app.lms.entity.Employee;
import com.app.lms.payload.EmployeeRequestDTO;
import com.app.lms.payload.EmployeeResponseDTO;
@Service
public interface IEmployeeService {
    public void postEmployee(EmployeeRequestDTO employeeRequestDTO);
    public EmployeeResponseDTO fetchEmployee(String email);
    public boolean UpdateEmployee(EmployeeRequestDTO employeeRequestDTO);
    public boolean deleteEmployee(String email);
    public Employee getEmployeeData(String email);
}

package com.app.lms.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.lms.entity.Department;
import com.app.lms.entity.Employee;
import com.app.lms.entity.Manager;
import com.app.lms.entity.Role;
import com.app.lms.exception.ResourceAlreadyExistException;
import com.app.lms.exception.ResourceNotFoundException;
import com.app.lms.payload.EmployeeForLeaveBalance;
import com.app.lms.payload.EmployeeRequestDTO;
import com.app.lms.payload.EmployeeResponseDTO;
import com.app.lms.repository.DepartmentRepository;
import com.app.lms.repository.EmployeeRespository;
import com.app.lms.repository.ManagerRepository;
import com.app.lms.repository.RoleRepository;
import com.app.lms.service.EmailService;
import com.app.lms.service.IEmployeeService;
import com.app.lms.utils.ProjectUtils;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements IEmployeeService {

	private EmployeeRespository employeeRespository;
	private ManagerRepository managerRepository;
	private RoleRepository roleRepository;
	private DepartmentRepository departmentRepository;
	private EmailService emailService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void postEmployee(EmployeeRequestDTO employeeRequestDTO) {
		String password = ProjectUtils.generatePassword();
		// System.out.println("Password:"+password);
		String encodedPassword = passwordEncoder.encode(password);
		employeeRequestDTO.setPassword(encodedPassword);
		if (employeeRequestDTO.getManagerid() == 1)
			employeeRequestDTO.setManagerid(1l);
		Employee employee = mapToEmployee(employeeRequestDTO, new Employee());
		if (employeeRespository.findByEmail(employeeRequestDTO.getEmail()).isPresent())
			throw new ResourceAlreadyExistException(
					"Email id is already exist in db Email:" + employeeRequestDTO.getEmail());

		employeeRespository.save(employee);
		// emailService.sendPassWordToEmail(password, employee.getEmail(),
		// employee.getFirstName());
		// System.out.println("Email sent successfully");
	}

	@Override
	public EmployeeResponseDTO fetchEmployee(String email) {
		Employee employee = employeeRespository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("Employee Not found for this email id:" + email));
		return mapToEmployeeDTO(employee, new EmployeeResponseDTO());
	}

	@Override
	public boolean UpdateEmployee(EmployeeRequestDTO employeeRequestDTO) {
		boolean isUpdated = false;
		Employee employee = employeeRespository.findByEmail(employeeRequestDTO.getEmail())
				.orElseThrow(() -> new ResourceNotFoundException(
						"Employee Not found for this email id:" + employeeRequestDTO.getEmail()));
		employee = mapToEmployee(employeeRequestDTO, employee);
		employeeRespository.save(employee);
		isUpdated = true;
		return isUpdated;
	}

	@Override
	public boolean deleteEmployee(String email) {
		boolean isDeleted = false;
		Employee employee = employeeRespository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("Employee Not found for this email id:" + email));
		employeeRespository.deleteById(employee.getEmployeeId());
		isDeleted = true;
		return isDeleted;
	}

	public EmployeeResponseDTO mapToEmployeeDTO(Employee employee, EmployeeResponseDTO employeeResponseDTO) {
		employeeResponseDTO.setId(employee.getEmployeeId());
		employeeResponseDTO.setEmployeeName(employee.getEmployeeName());
		;
		employeeResponseDTO.setEmail(employee.getEmail());
		employeeResponseDTO.setMobileNumber(employee.getMobileNumber());
		employeeResponseDTO.setRole(employee.getRoles());
		employeeResponseDTO.setManager(employee.getManager());
		employeeResponseDTO.setDepartment(employee.getDepartment());

		List<EmployeeForLeaveBalance> employeeforleavebalance = employee.getLeavebalance().stream()
				.map(leaveBalance -> {
					EmployeeForLeaveBalance employeeForLeaveBalance = new EmployeeForLeaveBalance();
					employeeForLeaveBalance.setLeaveType(leaveBalance.getLeavetype().getLeaveType());
					employeeForLeaveBalance.setDaysAvilable(leaveBalance.getAvailableDays());
					return employeeForLeaveBalance;
				}).collect(Collectors.toList());
		employeeResponseDTO.setLeavebalance(employeeforleavebalance);
		return employeeResponseDTO;

	}

	public Employee mapToEmployee(EmployeeRequestDTO employeeRequestDTO, Employee employee) {
		employee.setEmployeeName(employeeRequestDTO.getEmpName());
		employee.setEmail(employeeRequestDTO.getEmail());
		if (employee.getPassword() == null)
			employee.setPassword(employeeRequestDTO.getPassword());
		employee.setMobileNumber(employeeRequestDTO.getMobileNumber());
		Manager manager = managerRepository.findById(employeeRequestDTO.getManagerid()).orElseThrow(
				() -> new ResourceNotFoundException("Manager Not found in DB:" + employeeRequestDTO.getManagerid()));
		Role role = roleRepository.findById(employeeRequestDTO.getRoleid()).orElseThrow(
				() -> new ResourceNotFoundException("Role Not found in DB:" + employeeRequestDTO.getRoleid()));
		Department department = departmentRepository.findById(employeeRequestDTO.getDepartmentid())
				.orElseThrow(() -> new ResourceNotFoundException(
						"Department not found in DB:" + employeeRequestDTO.getDepartmentid()));
		employee.setManager(manager);
		employee.setDepartment(department);
		employee.setRoles(role);
		return employee;
	}

	@Override
	public Employee getEmployeeData(String email) {
		Employee employee = employeeRespository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("Email Id Not found in DB"));
		return employee;
	}

}

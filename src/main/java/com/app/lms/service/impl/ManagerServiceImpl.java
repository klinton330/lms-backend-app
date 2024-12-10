package com.app.lms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.app.lms.entity.Employee;
import com.app.lms.entity.Manager;
import com.app.lms.exception.ResourceNotFoundException;
import com.app.lms.payload.ManagerForEmployeesResponse;
import com.app.lms.payload.ManagerRequestDTO;
import com.app.lms.payload.ManagerResponseDTO;
import com.app.lms.repository.EmployeeRespository;
import com.app.lms.repository.ManagerRepository;
import com.app.lms.service.IManagerService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ManagerServiceImpl implements IManagerService {

	private ManagerRepository managerRepository;
	private EmployeeRespository employeeRespository;

	@Override
	public void createManager(ManagerRequestDTO managerRequestDTO) {

		Manager manager = toManager(managerRequestDTO, new Manager());
		Employee employee = employeeRespository.findByEmployeeName(manager.getManagerName())
				.orElseThrow(() -> new ResourceNotFoundException("Manager Name not found in employee DB"));
		if (employee != null)
			managerRepository.save(manager);
	}

	public Manager toManager(ManagerRequestDTO managerRequestDTO, Manager manager) {
		manager.setManagerName(managerRequestDTO.getManagerName());
		manager.setEmail(managerRequestDTO.getEmail());
		return manager;
	}

	public ManagerResponseDTO managerForEmployeesResponse(Manager manager, ManagerResponseDTO managerResponseDTO) {
		List<Employee> employees = manager.getEmployee();

		List<ManagerForEmployeesResponse> employeeResponses = employees.stream().map(emp -> {
			ManagerForEmployeesResponse response = new ManagerForEmployeesResponse();
			response.setId(emp.getEmployeeId());
			response.setEmployeeName(emp.getEmployeeName());
			response.setEmail(emp.getEmail());
			response.setMobileNumber(emp.getMobileNumber());
			response.setManagerName(emp.getManager().getManagerName());
			response.setDepartment(emp.getDepartment().getDeptName());
			return response;
		}).collect(Collectors.toList());

		managerResponseDTO.setId(manager.getId());
		managerResponseDTO.setManagerName(manager.getManagerName());
		managerResponseDTO.setEmail(manager.getEmail());
		managerResponseDTO.setEmployee(employeeResponses);

		return managerResponseDTO;
	}

	@Override
	public ManagerResponseDTO fetchManager(String name) {
		Manager manager = managerRepository.findByManagerName(name)
				.orElseThrow(() -> new ResourceNotFoundException("Manager Not found in DB"));
		return managerForEmployeesResponse(manager, new ManagerResponseDTO());
	}

	@Override
	@Transactional
	public boolean deleteManager(String managerName) {
		boolean isDeleted = false;
		Manager manager = managerRepository.findByManagerName(managerName)
				.orElseThrow(() -> new ResourceNotFoundException("Manager Not found in DB"));
		Manager Updatemanager = managerRepository.findById(1L)
				.orElseThrow(() -> new ResourceNotFoundException("Unassigned Not found in DB"));
		for (Employee e : manager.getEmployee()) {
			e.setManager(Updatemanager);
			employeeRespository.save(e);
		}
		managerRepository.deleteById(manager.getId());
		isDeleted = true;
		return isDeleted;
	}

	@Override
	public List<String> getAllManagerName() {
		List<String> managerNames = managerRepository.findAll().stream().map(x -> {
			return x.getManagerName();
		}).collect(Collectors.toList());
		return managerNames;
	}

	@Override
	public boolean UpdateManager(ManagerRequestDTO managerRequestDTO) {
		boolean isUpdated = false;
		Manager manager = managerRepository.findByManagerName(managerRequestDTO.getManagerName())
				.orElseThrow(() -> new ResourceNotFoundException("Manager Not found in DB"));
		manager = toManager(managerRequestDTO, manager);
		managerRepository.save(manager);
		isUpdated = true;
		return isUpdated;
	}

}

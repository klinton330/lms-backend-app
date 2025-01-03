package com.app.lms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.lms.entity.Employee;
import com.app.lms.entity.LeaveBalance;
import com.app.lms.entity.LeaveType;
import com.app.lms.exception.LeaveBalanceExistsException;
import com.app.lms.exception.ResourceNotFoundException;
import com.app.lms.payload.LeaveBalanceRequestDTO;
import com.app.lms.repository.EmployeeRespository;
import com.app.lms.repository.LeaveBalanceRepository;
import com.app.lms.repository.LeaveTypeRepository;
import com.app.lms.service.ILeaveBalance;

@Service
public class LeaveBalanceServiceImpl implements ILeaveBalance {

	@Autowired
	private EmployeeRespository employeeRespository;
	@Autowired
	private LeaveTypeRepository leaveTypeRepository;
	@Autowired
	private LeaveBalanceRepository leaveBalanceRepository;

	@Override
	public void postLeaveBalance(LeaveBalanceRequestDTO leaveBalanceRequestDTO) {
		Employee employee = employeeRespository.findById(leaveBalanceRequestDTO.getEmployeeid())
				.orElseThrow(() -> new ResourceNotFoundException(
						"Employee Not found with id:" + leaveBalanceRequestDTO.getEmployeeid()));
		LeaveType leaveType = leaveTypeRepository.findById(leaveBalanceRequestDTO.getLeavetypeid())
				.orElseThrow(() -> new ResourceNotFoundException(
						"Leavetype Not found with id:" + leaveBalanceRequestDTO.getLeavetypeid()));
		if (leaveBalanceRepository.existsByEmployeeAndLeavetype(employee, leaveType))
			throw new LeaveBalanceExistsException("Leave Balance already Posted");
		leaveBalanceRepository.save(mapToLeaveType(leaveBalanceRequestDTO, new LeaveBalance(), employee, leaveType));

	}

	public LeaveBalance mapToLeaveType(LeaveBalanceRequestDTO leaveBalanceRequestDTO, LeaveBalance leaveBalance,
			Employee employee, LeaveType leaveType) {
		leaveBalance.setEmployee(employee);
		leaveBalance.setLeavetype(leaveType);
		leaveBalance.setAvailableDays(leaveBalanceRequestDTO.getAvailabledays());
		return leaveBalance;
	}

	@Override
	public boolean updateLeaveBalance(Long id, LeaveBalanceRequestDTO leaveBalanceRequestDTO) {
		boolean isUpdated = false;
		LeaveBalance leaveBalance = leaveBalanceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
				"Employee Not found with id:" + leaveBalanceRequestDTO.getEmployeeid()));
		leaveBalance.setAvailableDays(leaveBalanceRequestDTO.getAvailabledays());
        if(leaveBalanceRequestDTO.getAvailabledays()==leaveBalance.getAvailableDays())
        	return true;
		leaveBalanceRepository.save(leaveBalance);
		isUpdated = true;
		return isUpdated;
	}

}

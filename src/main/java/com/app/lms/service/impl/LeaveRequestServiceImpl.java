package com.app.lms.service.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.lms.entity.ELeaveStatus;
import com.app.lms.entity.Employee;
import com.app.lms.entity.LeaveApproval;
import com.app.lms.entity.LeaveRequest;
import com.app.lms.entity.LeaveType;
import com.app.lms.exception.ResourceNotFoundException;
import com.app.lms.payload.LeaveAppliedDTO;
import com.app.lms.payload.LeaveRequestDTO;
import com.app.lms.repository.EmployeeRespository;
import com.app.lms.repository.LeaveApprovalRepository;
import com.app.lms.repository.LeaveRequestRepository;
import com.app.lms.repository.LeaveTypeRepository;
import com.app.lms.service.ILeaveRequestService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class LeaveRequestServiceImpl implements ILeaveRequestService {

	@Autowired
	private EmployeeRespository employeeRespository;
	@Autowired
	private LeaveTypeRepository leaveTypeRepository;
	@Autowired
	private LeaveApprovalRepository  leaveApprovalRepository ;
	@Autowired
	private LeaveRequestRepository leaveRequestRepository;
	
	
	@Override
	public LeaveAppliedDTO postLeaveRequest(LeaveRequestDTO leaveRequestDTO) {
		LeaveRequest leaveRequest=mapToLeaveRequest(leaveRequestDTO);
		Employee employee=employeeRespository.findById(leaveRequestDTO.getEmployeeId())
				.orElseThrow(()->new ResourceNotFoundException("Employee id Not found:"+leaveRequestDTO.getEmployeeId()));
		LeaveType leaveType = leaveTypeRepository.findById(leaveRequestDTO.getLeaveTypeId())
				.orElseThrow(() -> new ResourceNotFoundException(
						"Leavetype Not found with id:" + leaveRequestDTO.getLeaveTypeId()));
		leaveRequest.setEmployee(employee);
		leaveRequest.setManager(employee.getManager());
		leaveRequest.setLeaveType(leaveType);
		int calculate=calculateTotalDays(leaveRequest.getFromDate(),leaveRequest.getToDate());
		leaveRequest.setTotaldays(calculate);
		
		System.out.println(calculate);
		LeaveRequest afterLeaveRequest=leaveRequestRepository.save(leaveRequest);
		LeaveApproval leaveApproval=new LeaveApproval();
		leaveApproval.setManager(employee.getManager());
		leaveApproval.setComments(null);
		leaveApproval.setActionDate(null);
		leaveApproval.setStatus(ELeaveStatus.PENDING);
		leaveApproval.setLeaveRequest(afterLeaveRequest);
		LeaveApproval updateLeaveApproval=leaveApprovalRepository.save(leaveApproval);
		return leaveAppliedDTO(afterLeaveRequest, updateLeaveApproval);
		
	}
	
	public LeaveRequest mapToLeaveRequest(LeaveRequestDTO leaveRequestDTO) {
		LeaveRequest leaveRequest=new LeaveRequest();
		leaveRequest.setFromDate(leaveRequestDTO.getFromDate());
		leaveRequest.setToDate(leaveRequestDTO.getToDate());
		leaveRequest.setReason(leaveRequestDTO.getReason());
		return leaveRequest;
	}
	
	public LeaveAppliedDTO leaveAppliedDTO(LeaveRequest leaveRequest, LeaveApproval leaveApproval) {
		LeaveAppliedDTO leaveAppliedDTO=new LeaveAppliedDTO();
		leaveAppliedDTO.setLeaveId(leaveRequest.getId());
		leaveAppliedDTO.setLeaveType(leaveRequest.getLeaveType().getLeaveType());
		leaveAppliedDTO.setFromDate(leaveRequest.getFromDate());
		leaveAppliedDTO.setToDate(leaveRequest.getToDate());
		leaveAppliedDTO.setReason(leaveRequest.getReason());
		leaveAppliedDTO.setStatus(leaveApproval.getStatus().name());
		return leaveAppliedDTO;
	}
	
	public int calculateTotalDays(LocalDate fromDate,LocalDate toDate) {
		return 0;
	}

}

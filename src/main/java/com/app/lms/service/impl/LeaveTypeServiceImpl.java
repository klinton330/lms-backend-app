package com.app.lms.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.lms.entity.LeaveType;
import com.app.lms.exception.InvalidLeaveTypeException;
import com.app.lms.exception.ResourceNotFoundException;
import com.app.lms.payload.LeaveTypeRequestDTO;
import com.app.lms.payload.LeaveTypeResponseDTO;
import com.app.lms.repository.LeaveTypeRepository;
import com.app.lms.service.ILeaveTypeService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class LeaveTypeServiceImpl implements ILeaveTypeService {
	@Autowired
	private LeaveTypeRepository leaveTypeRepository;

	@Override
	public void postLeaveType(LeaveTypeRequestDTO leaveTypeRequestDTO) {
		if (leaveTypeRepository.existsByLeaveType(leaveTypeRequestDTO.getLeaveType()))
			throw new InvalidLeaveTypeException(leaveTypeRequestDTO.getLeaveType() + " already exists in DB");
		leaveTypeRepository.save(mapToLeaveType(leaveTypeRequestDTO, new LeaveType()));

	}

	@Override
	public List<LeaveTypeResponseDTO> getAllLeaveType() {
		List<LeaveType> allLeavTypeFromDB = leaveTypeRepository.findAll();
		List<LeaveTypeResponseDTO> allLeaveTypes = new ArrayList<>();
		for (LeaveType leaveType : allLeavTypeFromDB) {
			LeaveTypeResponseDTO leaveTypeResponseDTO = new LeaveTypeResponseDTO();
			leaveTypeResponseDTO.setLeaveType(leaveType.getLeaveType());
			leaveTypeResponseDTO.setDescription(leaveType.getDescription());
			leaveTypeResponseDTO.setMaxDays(leaveType.getMaxDays());
			allLeaveTypes.add(leaveTypeResponseDTO);
		}
		return allLeaveTypes;
	}

	@Override
	public boolean updateLeaveType(LeaveTypeRequestDTO leaveTypeRequestDTO) {
		boolean isUpdated = false;
		LeaveType leaveType = leaveTypeRepository.findByLeaveType(leaveTypeRequestDTO.getLeaveType())
				.orElseThrow(() -> new ResourceNotFoundException(
						"Leave type not found in DB:" + leaveTypeRequestDTO.getLeaveType()));
		leaveType = mapToLeaveType(leaveTypeRequestDTO, leaveType);
		leaveTypeRepository.save(leaveType);
		isUpdated = true;
		return isUpdated;
	}

	@Override
	public boolean deleteLeaveType(String leaveTypes) {
		boolean isDeleted = false;
		LeaveType leaveType = leaveTypeRepository.findByLeaveType(leaveTypes)
				.orElseThrow(() -> new ResourceNotFoundException("Leave type not found in DB:" + leaveTypes));
		leaveTypeRepository.deleteById(leaveType.getId());
		isDeleted = true;
		return isDeleted;
	}

	public LeaveType mapToLeaveType(LeaveTypeRequestDTO leaveTypeRequestDTO, LeaveType leaveType) {
		leaveType.setLeaveType(leaveTypeRequestDTO.getLeaveType());
		leaveType.setDescription(leaveTypeRequestDTO.getDescription());
		leaveType.setMaxDays(leaveTypeRequestDTO.getMaxDays());
		return leaveType;

	}

	public LeaveTypeRequestDTO mapToLeaveTypeRequestDTO(LeaveType leaveType, LeaveTypeRequestDTO leaveTypeRequestDTO) {
		leaveTypeRequestDTO.setLeaveType(leaveType.getLeaveType());
		leaveTypeRequestDTO.setDescription(leaveType.getDescription());
		leaveTypeRequestDTO.setMaxDays(leaveType.getMaxDays());
		return leaveTypeRequestDTO;
	}

}

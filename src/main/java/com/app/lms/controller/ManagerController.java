package com.app.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.lms.constants.EmployeeConstants;
import com.app.lms.constants.ManagerConstants;
import com.app.lms.payload.EmployeeResponseDTO;
import com.app.lms.payload.ManagerRequestDTO;
import com.app.lms.payload.ManagerResponseDTO;
import com.app.lms.payload.ResponseDTO;
import com.app.lms.service.IManagerService;

@RestController
@RequestMapping("/api/v1/manager")
public class ManagerController {

	@Autowired
	private IManagerService managerService;

	@PostMapping
	public ResponseEntity<ResponseDTO> createManager(@RequestBody ManagerRequestDTO managerRequestDTO) {
		managerService.createManager(managerRequestDTO);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDTO(ManagerConstants.STATUS_201, ManagerConstants.STATUS_201_MESSAGE));
	}

	@GetMapping
	public ResponseEntity<ManagerResponseDTO> getManager(@RequestParam String managerName) {
		ManagerResponseDTO managerResponseDTO = managerService.fetchManager(managerName);
		return ResponseEntity.status(HttpStatus.OK).body(managerResponseDTO);

	}

	@GetMapping("/all")
	public ResponseEntity<List<String>> getManagerNames() {
		List<String> managerNames = managerService.getAllManagerName();
		return ResponseEntity.status(HttpStatus.OK).body(managerNames);
	}

	@DeleteMapping
	public ResponseEntity<ResponseDTO> deleteEmployee(@RequestParam String managerName) {
		boolean isDeleted = managerService.deleteManager(managerName);
		if (isDeleted)
			return ResponseEntity.status(HttpStatus.NO_CONTENT)
					.body(new ResponseDTO(ManagerConstants.STATUS_200, ManagerConstants.STATUS_200_MESSAGE));
		else
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDTO(ManagerConstants.STATUS_500, ManagerConstants.STATUS_500_MESSAGE));
	}

	@PutMapping
	public ResponseEntity<ResponseDTO> updateManager(@RequestBody ManagerRequestDTO managerRequestDTO) {
		boolean isUpdated = managerService.UpdateManager(managerRequestDTO);
		if (isUpdated)
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDTO(ManagerConstants.STATUS_200, ManagerConstants.STATUS_200_MESSAGE));
		else
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDTO(ManagerConstants.STATUS_500, ManagerConstants.STATUS_500_MESSAGE));

	}
}

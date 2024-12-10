package com.app.lms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.lms.payload.EmployeeRequestDTO;
import com.app.lms.payload.ManagerRequestDTO;
import com.app.lms.payload.ManagerResponseDTO;
@Service
public interface IManagerService {
  public void createManager(ManagerRequestDTO managerRequestDTO);
  public ManagerResponseDTO fetchManager(@RequestParam String name);
  public boolean deleteManager(String managerName);
  public List<String> getAllManagerName();
  public boolean UpdateManager(ManagerRequestDTO managerRequestDTO);
}

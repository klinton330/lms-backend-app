package com.app.lms.payload;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LeaveRequestDTO {
	private Long employeeId;
    private Long leaveTypeId;
	private LocalDate fromDate;
    private LocalDate toDate;
    private String reason;
}

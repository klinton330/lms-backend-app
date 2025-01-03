package com.app.lms.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LeaveTypeRequestDTO {
	private String leaveType;
	private String description;
	private int maxDays;
}

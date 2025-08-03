package com.app.lms.payload;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LeaveAppliedDTO {
	
	private Long leaveId;
	private String leaveType;
	private LocalDate fromDate;
    private LocalDate toDate;
    private String reason;
    private String status;
    private String comment;
    private LocalDate LocalDate;

}

package com.app.lms.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LeaveBalanceRequestDTO {

	private Long employeeid;
	private Long leavetypeid;
	private Long availabledays;
}

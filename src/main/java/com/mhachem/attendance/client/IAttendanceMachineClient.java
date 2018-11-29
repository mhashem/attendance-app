package com.mhachem.attendance.client;

import java.util.List;

import com.mhachem.attendance.model.AttendanceDay;

public interface IAttendanceMachineClient {
	// todo add interface - so we are not machine dependent
	// todo move call to attendance machine here
	
	List<AttendanceDay> parseAttendanceDays(int employeeId, int month);
	
}

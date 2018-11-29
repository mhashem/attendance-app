package com.mhachem.attendance.service;

import java.util.List;

import com.mhachem.attendance.model.AttendanceDay;
import com.mhachem.attendance.model.AttendanceResult;

public interface IAttendanceService {

	AttendanceResult computeAttendance(int employeeId, int month, boolean useDefaults);
	
}

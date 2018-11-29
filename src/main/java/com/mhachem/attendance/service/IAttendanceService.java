package com.mhachem.attendance.service;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.mhachem.attendance.model.AttendanceResult;

public interface IAttendanceService {

	AttendanceResult computeAttendance(int employeeId, int month, boolean useDefaults) throws UnirestException;
	
}

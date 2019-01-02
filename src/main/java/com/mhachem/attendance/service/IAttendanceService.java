package com.mhachem.attendance.service;

import java.io.IOException;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.mhachem.attendance.model.AttendanceResult;

public interface IAttendanceService {

	AttendanceResult computeAttendance(int employeeId, int month, boolean useDefaults)
		throws UnirestException, IOException;

	AttendanceResult computeAttendance(int employeeId, int month, int year, boolean useDefaults)
		throws UnirestException, IOException;
}

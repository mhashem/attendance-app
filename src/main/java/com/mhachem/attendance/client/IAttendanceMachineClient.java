package com.mhachem.attendance.client;

import java.io.IOException;
import java.util.List;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.mhachem.attendance.model.AttendanceDay;

public interface IAttendanceMachineClient {
	// todo add interface - so we are not machine dependent
	// todo move call to attendance machine here
	
	List<AttendanceDay> parseAttendanceDays(int employeeId, int month) throws UnirestException, IOException;

	List<AttendanceDay> parseAttendanceDays(int employeeId, int month, int year) throws UnirestException, IOException;
}

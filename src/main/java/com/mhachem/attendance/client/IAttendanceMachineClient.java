package com.mhachem.attendance.client;

import java.io.IOException;
import java.util.List;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.mhachem.attendance.model.AttendanceDay;
import com.mhachem.attendance.model.v2.EmployeeAttendance;

public interface IAttendanceMachineClient {

	@Deprecated
	List<AttendanceDay> parseAttendanceDays(int employeeId, int month) throws UnirestException, IOException;

	@Deprecated
	List<AttendanceDay> parseAttendanceDays(int employeeId, int month, int year) throws UnirestException, IOException;

	// use version 2 which computes the following url result

	// http://10.100.0.201/if.cgi?redirect2=setting.htm&failure2=fail.htm&type2=txt_data&UID22=57&TID22=&select5=0&Fkey=255
	// &num=0&start_month=4&start_date=1&start_year=19&end_month=4&end_date=30&end_year=19&sel_all=1&head=1&event=1&fkey=1&Export=EXPORT

	List<EmployeeAttendance> getEmployeeAttendance(int employeeId) throws UnirestException, IOException;

	List<EmployeeAttendance> getEmployeeAttendance(int employeeId, int month) throws UnirestException, IOException;

	List<EmployeeAttendance> getEmployeeAttendance(int employeeId, int month, int year)
		throws UnirestException, IOException;

}

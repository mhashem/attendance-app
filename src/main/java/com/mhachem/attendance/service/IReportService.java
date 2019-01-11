package com.mhachem.attendance.service;

import java.util.List;

import com.mhachem.attendance.model.AttendanceResult;
import com.mhachem.attendance.model.EmployeeAttendance;
import com.mhachem.attendance.model.context.AttendanceQueryContext;

public interface IReportService {
	
	List<AttendanceResult> report();
	
	List<EmployeeAttendance> report(int[] ids, AttendanceQueryContext ctx);
}

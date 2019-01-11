package com.mhachem.attendance.service;

import java.util.List;

import com.mhachem.attendance.model.AttendanceResult;
import com.mhachem.attendance.model.EmployeeAttendance;
import com.mhachem.attendance.model.context.AttendanceQueryContext;
import com.mhachem.attendance.utils.IOUtils;

public interface IReportService {
	
	List<AttendanceResult> report();

	List<EmployeeAttendance> report(AttendanceQueryContext ctx, IOUtils.ProgressListener progressListener);
}

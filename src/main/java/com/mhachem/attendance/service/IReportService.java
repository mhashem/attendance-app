package com.mhachem.attendance.service;

import java.util.List;

import com.mhachem.attendance.model.AttendanceResult;
import com.mhachem.attendance.model.context.AttendanceQueryContext;

public interface IReportService {
	
	List<AttendanceResult> report();
	
	List<AttendanceResult> report(int[] ids, AttendanceQueryContext ctx);
}

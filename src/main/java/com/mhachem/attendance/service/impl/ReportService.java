package com.mhachem.attendance.service.impl;

import static org.slf4j.LoggerFactory.getLogger;

import java.io.IOException;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mhachem.attendance.model.AttendanceResult;
import com.mhachem.attendance.model.Employee;
import com.mhachem.attendance.model.EmployeeAttendance;
import com.mhachem.attendance.model.context.AttendanceQueryContext;
import com.mhachem.attendance.service.IEmployeeService;
import com.mhachem.attendance.service.IReportService;
import com.mhachem.attendance.utils.IOUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService implements IReportService {

	private static final Logger logger = getLogger(ReportService.class);
	
	private final IEmployeeService employeeService;
	private final AttendanceService attendanceService;

	@Autowired
	public ReportService(AttendanceService attendanceService, IEmployeeService employeeService) {
		this.attendanceService = attendanceService;
		this.employeeService = employeeService;
	}

	@Override
	public List<AttendanceResult> report() {
		return ImmutableList.of();
	}

	@Override
	public List<EmployeeAttendance> report(AttendanceQueryContext ctx,
		IOUtils.ProgressListener progressListener) {
		
		List<EmployeeAttendance> employeeAttendances = Lists.newArrayList();
		
		ctx.getIds().forEach( id -> {
			try {
				// todo use the data provider to get Employee name
				Employee employee = findEmployee(id);
				AttendanceResult attendanceResult =
					attendanceService.computeAttendance(id, ctx.getMonth(), ctx.getYear(), ctx.isUseDefaults());
				employeeAttendances.add(EmployeeAttendance.make(employee, attendanceResult));

				// todo use RxJava instead
				progressListener.onProgress(1);

			} catch (UnirestException | IOException e) {
				logger.error(e.getMessage(), e);
			}
		});
		return employeeAttendances;
	}
	
	private Employee findEmployee(int id) {
		return employeeService.findById(id).orElseGet(() -> Employee.from(id));
	}
	
}

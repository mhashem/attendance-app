package com.mhachem.attendance.service.impl;

import static org.slf4j.LoggerFactory.getLogger;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mhachem.attendance.model.AttendanceResult;
import com.mhachem.attendance.model.context.AttendanceQueryContext;
import com.mhachem.attendance.service.IReportService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService implements IReportService {

	private static final Logger logger = getLogger(ReportService.class);
	
	private final AttendanceService attendanceService;

	@Autowired
	public ReportService(AttendanceService attendanceService) {
		this.attendanceService = attendanceService;
	}

	@Override
	public List<AttendanceResult> report() {
		return ImmutableList.of();
	}

	@Override
	public List<AttendanceResult> report(int[] ids, AttendanceQueryContext ctx) {

		List<AttendanceResult> attendanceResults = Lists.newArrayList();
		
		for (int id : ids) {
			try {
				attendanceService.computeAttendance(id, ctx.getMonth(), ctx.getYear(), ctx.isUseDefaults());
			} catch (UnirestException | IOException e) {
				logger.error(e.getMessage(), e);
			}
		}

		return attendanceResults;
	}
	
}

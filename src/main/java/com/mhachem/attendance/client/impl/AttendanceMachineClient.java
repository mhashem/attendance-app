package com.mhachem.attendance.client.impl;

import static org.slf4j.LoggerFactory.getLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mhachem.attendance.client.IAttendanceMachineClient;
import com.mhachem.attendance.config.AttendanceConfig;
import com.mhachem.attendance.model.AttendanceDay;
import com.mhachem.attendance.utils.Constants;
import com.mhachem.attendance.utils.Helper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AttendanceMachineClient implements IAttendanceMachineClient {

	private static final Logger logger = getLogger(AttendanceMachineClient.class);
	
	private AttendanceConfig attendanceConfig;

	@Autowired
	public AttendanceMachineClient(AttendanceConfig attendanceConfig) {
		this.attendanceConfig = attendanceConfig;
	}

	@Override
	public List<AttendanceDay> parseAttendanceDays(int employeeId, int month) {

		LocalDate seedDate = LocalDate.now().withMonth(month);

		HttpResponse<InputStream> httpResponse = null;
		try {
			httpResponse = Unirest.get(attendanceConfig.getMachine().getAddress())
				.queryString("redirect", "report.htm")
				.queryString("failure", "fail.htm")
				.queryString("type", "attend_report")
				.queryString("sel_all", "2")
				.queryString("UID", employeeId)
				.queryString("start_month", seedDate.getMonthValue())
				.queryString("start_date", "1")
				.queryString("start_year", seedDate.format(DateTimeFormatter.ofPattern("yy")))
				.queryString("end_month", seedDate.getMonthValue())
				.queryString("end_date", seedDate.lengthOfMonth())
				.queryString("end_year", seedDate.format(DateTimeFormatter.ofPattern("yy")))
				.queryString("Export", "EXPORT")
				.header("Authorization", "Basic " + attendanceConfig.getMachine().getAccessToken())
				.header("Connection", "keep-alive")
				.header("Content-Type", "application/vnd.ms-excel")
				.asBinary();

			logger.info("Status Code {}", httpResponse.getStatus());
			if (httpResponse.getStatus() == 200) {
				return parseStream(httpResponse.getRawBody());
			}
			else {
				logger.warn("Failed to get file for employee id {} - month {}", employeeId, month);
			}
		} catch (UnirestException e) {
			logger.error(e.getMessage(), e);
		}
		return ImmutableList.of();
	}
	
	private List<AttendanceDay> parseStream(InputStream inputStream) {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
			return reader.lines().map(line -> line.split("\t"))
				.filter(strings -> Constants.WORKING_DAYS.contains(strings[0]))
				.map(Helper::parseDay)
				.collect(Collectors.toList());

		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return ImmutableList.of();
		}
	}
	
}

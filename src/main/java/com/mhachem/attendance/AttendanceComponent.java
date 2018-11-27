package com.mhachem.attendance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableSet;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mhachem.attendance.config.AttendanceConfig;
import com.mhachem.attendance.model.AttendanceDay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class AttendanceComponent {

	private Logger logger = LoggerFactory.getLogger(AttendanceComponent.class);

	private static final int TOTAL_MINUTES_PER_DAY = 9 * 60;
	private static final Set<String> WORKING_DAYS = ImmutableSet.of("MON", "TUE", "WED", "THU", "FRI");
	
	private final AttendanceConfig attendanceConfig;

	@Autowired
	public AttendanceComponent(AttendanceConfig attendanceConfig) {
		this.attendanceConfig = attendanceConfig;
	}

	@ShellMethod("Compute attendance time - result in minutes")
	public void attendance(@ShellOption String uid, @ShellOption String month, 
		@ShellOption(defaultValue = "d") String defaultTimes) {

		try {
			
			boolean useDefaultTimes = defaultTimes.equalsIgnoreCase("d"); 
			
			LocalDate seedDate = LocalDate.now().withMonth(Integer.parseInt(month));

			HttpResponse<InputStream> httpResponse = Unirest.get(attendanceConfig.getMachine().getAddress())
				.queryString("redirect", "report.htm")
				.queryString("failure", "fail.htm")
				.queryString("type", "attend_report")
				.queryString("sel_all", "2")
				.queryString("UID", uid)
				.queryString("start_month", seedDate.getMonthValue())
				.queryString("start_date", "1")
				.queryString("start_year", seedDate.format(DateTimeFormatter.ofPattern("yy")))
				.queryString("end_month", seedDate.getMonthValue())
				.queryString("end_date", seedDate.lengthOfMonth())
				.queryString("end_year", seedDate.format(DateTimeFormatter.ofPattern("yy")))
				.queryString("Export", "EXPORT")
				.header("Authorization", "Basic YWRtaW46YWRtaW4=")
				.header("Connection", "keep-alive")
				.header("Content-Type", "application/vnd.ms-excel")
				.asBinary();

			logger.info("Status Code {}", httpResponse.getStatus());
			if (httpResponse.getStatus() == 200) {
				computeAttendance(httpResponse.getRawBody(), useDefaultTimes);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

	private void computeAttendance(InputStream inputStream, boolean useDefaultTimes) throws IOException {
		
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
			List<AttendanceDay> attendanceDays = reader
				.lines()
				.map(line -> line.split("\t"))
				.filter(strings -> WORKING_DAYS.contains(strings[0]))
				.map(Utils::parseDay)
				.collect(Collectors.toList());

			int result = 0;
			int computedDays = 0;

			for (AttendanceDay attendanceDay : attendanceDays) {

				if (useDefaultTimes) {
					attendanceDay.applyDefaults();
				}
				
				if (attendanceDay.isWorkingDay()) {
					long workedMinutes = Duration.between(attendanceDay.getIn(), attendanceDay.getOut()).toMinutes();
					long diff = workedMinutes - TOTAL_MINUTES_PER_DAY;
					result += diff;
					computedDays++;
				}
			}
			logger.info("Your current state (in minutes) for ({}) working days is: {}", computedDays, result);
			logger.info("Expected working hours by end of the month: {}", attendanceDays.size() * 9);
		}
	}
}

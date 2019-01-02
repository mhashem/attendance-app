package com.mhachem.attendance.shell;

import java.time.LocalDate;

import com.mhachem.attendance.model.AttendanceResult;
import com.mhachem.attendance.service.impl.AttendanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.util.StringUtils;

@ShellComponent
public class AttendanceComponent {

	private Logger logger = LoggerFactory.getLogger(AttendanceComponent.class);

	private final AttendanceService attendanceService;

	@Autowired
	public AttendanceComponent(AttendanceService attendanceService) {
		this.attendanceService = attendanceService;
	}

	@ShellMethod("Compute attendance time - result in minutes")
	public void attendance(
		@ShellOption
			String uid) {
		LocalDate now = LocalDate.now();
		try {
			this.report(
				attendanceService.computeAttendance(Integer.parseInt(uid), now.getMonthValue(), now.getYear(), true));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@ShellMethod("Compute attendance time - result in minutes")
	public void attendance2(
		@ShellOption
			String uid,
		@ShellOption
			String month,
		@ShellOption(defaultValue = "2019")
			String year,
		@ShellOption(defaultValue = "d") String defaultTimes) {

		try {
			boolean useDefaultTimes = defaultTimes.equalsIgnoreCase("d");

			if (StringUtils.isEmpty(year)) {
				this.report(attendanceService
					.computeAttendance(Integer.parseInt(uid), Integer.parseInt(month), useDefaultTimes));
			} else {
				this.report(attendanceService
					.computeAttendance(Integer.parseInt(uid), Integer.parseInt(month), Integer.parseInt(year),
						useDefaultTimes));
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	private void report(AttendanceResult attendanceResult) {
		logger.warn("-------------------------------------------------------------------------------------------");
		if (attendanceResult.getTimeGap() < 0) {
			logger.info("Your current state (in minutes) for ({}) working days is: {} \uD83D\uDE13",
				attendanceResult.getComputedDays(), attendanceResult.getTimeGap());
		} else {
			logger.info("Your current state (in minutes) for ({}) working days is: {} \uD83D\uDE01",
				attendanceResult.getComputedDays(), attendanceResult.getTimeGap());
		}
		logger.info("Expected working hours by end of the month: {}", attendanceResult.getAttendanceDays().size() * 9);
		logger.warn("-------------------------------------------------------------------------------------------");
		logger.warn("\u001B[1mAfter 10 attempts the free License will expire and you will have to pay for using this app");
		logger.warn("-------------------------------------------------------------------------------------------");
	}

}

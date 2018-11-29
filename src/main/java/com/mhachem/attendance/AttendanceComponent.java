package com.mhachem.attendance;

import com.mhachem.attendance.model.AttendanceResult;
import com.mhachem.attendance.service.impl.AttendanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class AttendanceComponent {

	private Logger logger = LoggerFactory.getLogger(AttendanceComponent.class);

	private final AttendanceService attendanceService;

	@Autowired
	public AttendanceComponent(AttendanceService attendanceService) {
		this.attendanceService = attendanceService;
	}

	@ShellMethod("Compute attendance time - result in minutes")
	public void attendance(@ShellOption String uid, @ShellOption String month, 
		@ShellOption(defaultValue = "d") String defaultTimes) {

		try {
			boolean useDefaultTimes = defaultTimes.equalsIgnoreCase("d");
			this.report(
				attendanceService.computeAttendance(Integer.parseInt(uid), Integer.parseInt(month), useDefaultTimes));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

	private void report(AttendanceResult attendanceResult) {
		logger.info("Your current state (in minutes) for ({}) working days is: {}", attendanceResult.getComputedDays(),
			attendanceResult.getTimeGap());
		logger.info("Expected working hours by end of the month: {}", attendanceResult.getAttendanceDays().size() * 9);
	}

}

package com.mhachem.attendance.shell;

import java.time.LocalDate;
import java.util.List;

import com.google.common.collect.Lists;
import com.mhachem.attendance.model.AttendanceResult;
import com.mhachem.attendance.model.EmployeeAttendance;
import com.mhachem.attendance.model.context.AttendanceQueryContext;
import com.mhachem.attendance.service.IReportService;
import com.mhachem.attendance.service.impl.AttendanceService;
import me.tongfei.progressbar.ProgressBar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.util.StringUtils;

@ShellComponent
public class AttendanceComponent {

	private static final String LINE = "-------------------------------------------------" 
		+ "------------------------------------------";

	private static final String SMALL_LINE = "----------------------------------";
	
	private static final String SAD_FACE = "\uD83D\uDE13";
	private static final String SMILE_FACE = "\uD83D\uDE01";

	private Logger logger = LoggerFactory.getLogger(AttendanceComponent.class);

	private final IReportService reportService;
	
	private final AttendanceService attendanceService;

	@Autowired
	public AttendanceComponent(AttendanceService attendanceService, IReportService reportService) {
		this.attendanceService = attendanceService;
		this.reportService = reportService;
	}

	@ShellMethod("Compute attendance time - result in minutes")
	public void attendance(
		@ShellOption
			String uid) {
		LocalDate now = LocalDate.now();
		try {
			this.writeToShellStream(
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
				this.writeToShellStream(attendanceService
					.computeAttendance(Integer.parseInt(uid), Integer.parseInt(month), useDefaultTimes));
			} else {
				this.writeToShellStream(attendanceService
					.computeAttendance(Integer.parseInt(uid), Integer.parseInt(month), Integer.parseInt(year),
						useDefaultTimes));
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	@ShellMethod("Compute multiple employees attendance")
	public void report() {

		AttendanceQueryContext ctx =
			new AttendanceQueryContext(Lists.newArrayList(57, 11, 27, 86), LocalDate.now().getMonthValue(),
				LocalDate.now().getYear(), true);

		try (ProgressBar progressBar = new ProgressBar("Employees Attendance Computation", ctx.getIds().size())) {
			List<EmployeeAttendance> reportResult =
				reportService.report(ctx, progressBar::stepTo, (message, throwable) -> {
				});

			logger.info(SMALL_LINE);
			logger.info("ID\tName\t\t\tDays\tState");

			reportResult.stream().sorted().forEach(employeeAttendance -> {
				logger.info(SMALL_LINE);
				logger.info("{}\t{}\t{}\t\t{}", employeeAttendance.getEmployee().getId(),
					employeeAttendance.getEmployee().getName(),
					employeeAttendance.getAttendanceResult().getComputedDays(),
					String.format("%.2f", ((float)employeeAttendance.getAttendanceResult().getTimeGap() / 60)));
			});
			logger.info(SMALL_LINE);
		}
	}
	
	private void writeToShellStream(AttendanceResult attendanceResult) {
		logger.warn(LINE);
		if (attendanceResult.getTimeGap() < 0) {
			logger.info("Your current state (in minutes) for ({}) working days is: {} {}",
				attendanceResult.getComputedDays(), attendanceResult.getTimeGap(), SAD_FACE);
		} else {
			logger.info("Your current state (in minutes) for ({}) working days is: {} {}",
				attendanceResult.getComputedDays(), attendanceResult.getTimeGap(), SMILE_FACE);
		}
		logger.info("Expected working hours by end of the month: {}", attendanceResult.getAttendanceDays().size() * 9);
		logger.warn(LINE);
		logger.warn("\u001B[1mAfter 10 attempts the free License will expire and you will have to pay for using this app");
		logger.warn(LINE);
	}


}

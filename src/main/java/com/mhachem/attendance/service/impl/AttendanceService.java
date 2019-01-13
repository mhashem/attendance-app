package com.mhachem.attendance.service.impl;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.mhachem.attendance.client.IAttendanceMachineClient;
import com.mhachem.attendance.model.AttendanceDay;
import com.mhachem.attendance.model.AttendanceResult;
import com.mhachem.attendance.service.IAttendanceService;
import com.mhachem.attendance.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendanceService implements IAttendanceService {

	private final IAttendanceMachineClient attendanceMachineClient;

	@Autowired
	public AttendanceService(IAttendanceMachineClient attendanceMachineClient) {
		this.attendanceMachineClient = attendanceMachineClient;
	}

	@Override
	public AttendanceResult computeAttendance(int employeeId, int month, boolean useDefaults)
		throws UnirestException, IOException {
		return this.computeAttendance(this.attendanceMachineClient.parseAttendanceDays(employeeId, month), useDefaults);
	}

	@Override
	public AttendanceResult computeAttendance(int employeeId, int month, int year, boolean useDefaults)
		throws UnirestException, IOException {
		return this
			.computeAttendance(this.attendanceMachineClient.parseAttendanceDays(employeeId, month, year), useDefaults);
	}

	protected AttendanceResult computeAttendance(List<AttendanceDay> attendanceDays, boolean useDefaultTimes) {

		AttendanceResult attendanceResult = new AttendanceResult();

		int result = 0;
		int computedDays = 0;

		for (AttendanceDay attendanceDay : attendanceDays) {

			if (useDefaultTimes) {
				attendanceDay.applyDefaults();
			}

			if (attendanceDay.isWorkingDay()) {
				long workedMinutes = Duration.between(attendanceDay.getIn(), attendanceDay.getOut()).toMinutes();
				long diff = workedMinutes - Constants.TOTAL_MINUTES_PER_DAY;
				result += diff;
				computedDays++;
			}
		}

		attendanceResult.setTimeGap(result);
		attendanceResult.setComputedDays(computedDays);
		attendanceResult.setAttendanceDays(attendanceDays);

		return attendanceResult;
	}
}

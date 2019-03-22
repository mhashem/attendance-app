package com.mhachem.attendance.service.impl;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.mhachem.attendance.client.IAttendanceMachineClient;
import com.mhachem.attendance.model.AttendanceDay;
import com.mhachem.attendance.model.AttendanceResult;
import com.mhachem.attendance.service.IAttendanceService;

import com.google.common.collect.ImmutableList;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AttendanceServiceIT {

	@Mock
	private IAttendanceMachineClient client;
	
	private IAttendanceService attendanceService;

	@Before
	public void setup() {
		attendanceService = new AttendanceService(client);
	}

	@Test
	public void testComputeAttendance() throws IOException, UnirestException {
		// given
		when(client.parseAttendanceDays(anyInt(), anyInt())).thenReturn(makeAttendanceDays());
		
		// when
		AttendanceResult attendanceResult = attendanceService.computeAttendance(57, 11, true);

		// then
		Assertions.assertThat(attendanceResult).isNotNull();
		Assertions.assertThat(attendanceResult.getComputedDays()).isEqualTo(5);
		Assertions.assertThat(attendanceResult.getTimeGap()).isEqualTo(0);
	}
	
	private List<AttendanceDay> makeAttendanceDays() {
		LocalDate seedDate = LocalDate.now().with(DayOfWeek.MONDAY);

		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("hh:mma");

		LocalTime in = LocalTime.parse("08:00AM", pattern);
		LocalTime out = LocalTime.parse("05:00PM", pattern);

		return ImmutableList.of(
			this.makeAttendanceDay(seedDate, in, out),
			this.makeAttendanceDay(seedDate.with(DayOfWeek.TUESDAY), in, out),
			this.makeAttendanceDay(seedDate.with(DayOfWeek.WEDNESDAY), in, out),
			this.makeAttendanceDay(seedDate.with(DayOfWeek.THURSDAY), in, out),
			this.makeAttendanceDay(seedDate.with(DayOfWeek.FRIDAY), in, out)
		);
	}
	
	private AttendanceDay makeAttendanceDay(LocalDate date, LocalTime in, LocalTime out) {
		AttendanceDay attendanceDay = new AttendanceDay();
		attendanceDay.setDate(date);
		attendanceDay.setIn(in);
		attendanceDay.setOut(out);
		return attendanceDay;
	}

}

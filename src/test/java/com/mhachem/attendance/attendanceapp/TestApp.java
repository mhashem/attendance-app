package com.mhachem.attendance.attendanceapp;

import java.io.IOException;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

public class TestApp {

	private static final Set<String> VALID_DAYS = ImmutableSet.of("MON", "TUE", "WED", "THU", "FRI");

	private static final int TOTAL_MINUTES_PER_DAY = 9 * 60;
	
	public static void main(String[] args) throws IOException {
/*
		InputStream inputStream = Files.newInputStream(Paths.get("C:/Users/mhachem/Downloads/102518-27.xls"));
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
			List<AttendanceDay> attendanceDays = reader
				.lines()
				.map(line -> line.split("\t"))
				.filter(strings -> VALID_DAYS.contains(strings[0]))
				.map(Utils::parseDay)
				.collect(Collectors.toList());
			
			int result = 0;
			int computedDays = 0;

			for (AttendanceDay attendanceDay : attendanceDays) {
				if (!attendanceDay.isWorkingDay()) {
					long workedMinutes = Duration.between(attendanceDay.getIn(), attendanceDay.getOut()).toMinutes();
					long diff = workedMinutes - TOTAL_MINUTES_PER_DAY;
					result += diff;
					computedDays++;
				}
			}
			System.out.println("Your current state (in minutes) for ({}) working days is: " + result);
			System.out.println("Expected working hours by end of the month: " + attendanceDays.size() * 9);
		}
*/
	}
}

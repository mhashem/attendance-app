package com.mhachem.attendance;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.mhachem.attendance.model.AttendanceDay;

class Utils {

	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");

	private static final String DEFAULT_TIME_VALUE = "--:--";
	private static final String DEFAULT_DATE_VALUE = "----";

	private Utils() {
		// constructor
	}
	
	static AttendanceDay parseDay(String[] strings) {
		AttendanceDay day = new AttendanceDay();
		day.setDate(parseDate(strings[1]));
		day.setIn(parseTime(strings[2]));
		day.setOut(parseTime(strings[3]));
		day.setLateIn(parseTime(strings[4]));
		day.setEarly(parseTime(strings[5]));
		day.setOvertime(parseTime(strings[6]));
		return day;
	}

	private static LocalDate parseDate(String dateStr) {
		return dateStr.equalsIgnoreCase(DEFAULT_DATE_VALUE) ? null : LocalDate.parse(dateStr, DATE_TIME_FORMATTER);
	}

	private static LocalTime parseTime(String timeStr) {
		return timeStr.equalsIgnoreCase(DEFAULT_TIME_VALUE) || timeStr.equalsIgnoreCase(DEFAULT_DATE_VALUE) ?
			null : LocalTime.parse(timeStr);
	}


}

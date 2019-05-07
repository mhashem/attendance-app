package com.mhachem.attendance.model.v2;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class AttendanceRecord implements Comparable<AttendanceRecord> {

	private LocalDate date;
	private LocalTime time;
	private AttendanceRecordType type;

	@Override
	public int compareTo(AttendanceRecord that) {

		if (this.date.isBefore(that.date)) {
			return -1;
		}

		if (this.date.isAfter(that.date)) {
			return 1;
		}

		if (this.time.isBefore(that.time)) {
			return -1;
		}

		if (this.time.isAfter(that.time)) {
			return 1;
		}

		return 0;
	}
}

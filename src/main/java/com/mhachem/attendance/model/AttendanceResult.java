package com.mhachem.attendance.model;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;

public class AttendanceResult {

	private int timeGap;
	private int computedDays;
	private List<AttendanceDay> attendanceDays = Lists.newArrayList();

	public int getTimeGap() {
		return timeGap;
	}

	public void setTimeGap(int timeGap) {
		this.timeGap = timeGap;
	}

	public int getComputedDays() {
		return computedDays;
	}

	public void setComputedDays(int computedDays) {
		this.computedDays = computedDays;
	}

	public void setAttendanceDays(List<AttendanceDay> attendanceDays) {
		this.attendanceDays.addAll(attendanceDays);
	}

	public List<AttendanceDay> getAttendanceDays() {
		return Collections.unmodifiableList(attendanceDays);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) 
			return true;
		
		if (o == null || getClass() != o.getClass()) 
			return false;
		
		AttendanceResult that = (AttendanceResult) o;
		return Objects.equals(getTimeGap(), that.getTimeGap())
			&& Objects.equals(getComputedDays(), that.getComputedDays())
			&& this.attendanceDays.containsAll(that.attendanceDays);
	}

	@Override
	public int hashCode() {
		return Objects.hash(getTimeGap(), getAttendanceDays());
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(AttendanceResult.class)
			.add("timeGap", timeGap)
			.add("computedDays", computedDays)
			.add("attendanceDays", attendanceDays)
			.toString();
	}
}

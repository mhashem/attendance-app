package com.mhachem.attendance.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class AttendanceDay implements Comparable<AttendanceDay> {
	
	private LocalDate date;
	private LocalTime in;
	private LocalTime out;
	private LocalTime lateIn;
	private LocalTime early;
	private LocalTime leave;
	private LocalTime overtime;
	private String workingHours;

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getIn() {
		return in;
	}

	public void setIn(LocalTime in) {
		this.in = in;
	}

	public LocalTime getOut() {
		return out;
	}

	public void setOut(LocalTime out) {
		this.out = out;
	}

	public LocalTime getLateIn() {
		return lateIn;
	}

	public void setLateIn(LocalTime lateIn) {
		this.lateIn = lateIn;
	}

	public LocalTime getEarly() {
		return early;
	}

	public void setEarly(LocalTime early) {
		this.early = early;
	}

	public LocalTime getLeave() {
		return leave;
	}

	public void setLeave(LocalTime leave) {
		this.leave = leave;
	}

	public LocalTime getOvertime() {
		return overtime;
	}

	public void setOvertime(LocalTime overtime) {
		this.overtime = overtime;
	}

	public String getWorkingHours() {
		return workingHours;
	}

	public void setWorkingHours(String workingHours) {
		this.workingHours = workingHours;
	}

	public boolean isWorkingDay() {
		return in != null && out != null;
	}

	public void applyDefaults() {
		if (this.in != null && this.out == null) {
			this.out = LocalTime.parse("17:00");
			return;
		}
		
		if (this.in == null && this.out != null) {
			this.in = LocalTime.parse("08:00");
		}
	}

	@Override
	public boolean equals(Object o) {
		
		if (this == o) {
			return true;
		}
		
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		
		AttendanceDay that = (AttendanceDay) o;
		return Objects.equals(getDate(), that.getDate()) && Objects.equals(getIn(), that.getIn()) && Objects
			.equals(getOut(), that.getOut()) && Objects.equals(getLateIn(), that.getLateIn()) && Objects
			.equals(getEarly(), that.getEarly()) && Objects.equals(getLeave(), that.getLeave()) && Objects
			.equals(getOvertime(), that.getOvertime()) && Objects.equals(getWorkingHours(), that.getWorkingHours());
	}

	@Override
	public int compareTo(AttendanceDay o) {
		if (this.date.isBefore(o.date)) {
			return -1;
		}
		if (this.date.isAfter(o.date)) {
			return 1;
		}
		return 0;
	}
	
	@Override
	public int hashCode() {
		return Objects
			.hash(getDate(), getIn(), getOut(), getLateIn(), getEarly(), getLeave(), getOvertime(), getWorkingHours());
	}

	@Override
	public String toString() {
		return "Day{" + "date=" + date + ", in=" + in + ", out=" + out + ", lateIn=" + lateIn + ", early=" + early
			+ ", leave=" + leave + ", overtime=" + overtime + ", workingHours='" + workingHours + '\'' + '}';
	}
	
}

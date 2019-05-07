package com.mhachem.attendance.model;

import java.util.Objects;

import com.google.common.base.MoreObjects;
import org.springframework.util.comparator.Comparators;

@Deprecated
public class EmployeeAttendance implements Comparable<EmployeeAttendance> {

	private Employee employee;
	private AttendanceResult attendanceResult;

	private EmployeeAttendance() {
		// empty
	}

	public static EmployeeAttendance make(Employee employee, AttendanceResult attendanceResult) {
		EmployeeAttendance employeeAttendance = new EmployeeAttendance();
		employeeAttendance.employee = employee;
		employeeAttendance.attendanceResult = attendanceResult;
		return employeeAttendance;
	}

	public Employee getEmployee() {
		return employee;
	}

	public AttendanceResult getAttendanceResult() {
		return attendanceResult;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		EmployeeAttendance that = (EmployeeAttendance) o;
		return Objects.equals(getEmployee(), that.getEmployee()) && Objects
			.equals(getAttendanceResult(), that.getAttendanceResult());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getEmployee(), getAttendanceResult());
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(EmployeeAttendance.class)
			.add("employee", getEmployee())
			.add("attendanceResult", getAttendanceResult())
			.toString();
	}

	@Override
	public int compareTo(EmployeeAttendance o) {
		return Comparators.comparable().compare(this.getEmployee().getId(), o.getEmployee().getId());
	}
}
